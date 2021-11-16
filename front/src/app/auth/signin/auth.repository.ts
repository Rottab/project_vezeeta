export interface AuthRepository {
  token: string;
  type: string;
  username: string;
  expiresIn: number;
  roles: string[];
}
