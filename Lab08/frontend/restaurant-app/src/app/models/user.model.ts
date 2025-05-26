export interface User {
  username: string;
  firstName: string;
  lastName: string;
  userGroups: UserGroup[];
}

export interface UserGroup {
  role: string;
}