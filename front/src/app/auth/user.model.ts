export class User {
  constructor(public username: string,
              public token: string,
              public type: string,
              public expiresIn: number,
              public roles: string[]) {
  }
}
