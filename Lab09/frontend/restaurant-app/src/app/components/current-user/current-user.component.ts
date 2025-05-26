import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-current-user',
  standalone: false,
  templateUrl: './current-user.component.html',
  styleUrl: './current-user.component.scss'
})
export class CurrentUserComponent implements OnInit{
  currentUser!: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(user => {
      console.log('Current user:', user);
      this.currentUser = user;
    });
  }

}
