package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.model.UserInfo;
import hr.tvz.knezevic.njamapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (userInfo == null) throw new UsernameNotFoundException(username);

        String[] roles = userInfo.getUserGroups()
                .stream()
                .map(role -> role.getRole().name())
                .toArray(String[]::new);

        return User.withUsername(username)
                .password(userInfo.getPassword())
                .roles(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
