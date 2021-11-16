export class User {
  constructor(private _username: String,
              private _firstName: string,
              private _lastName: string,
              private _email: string
  ) {
  }


  get username(): String {
    return this._username;
  }

  get firstName(): string {
    return this._firstName;
  }

  get lastName(): string {
    return this._lastName;
  }

  get email(): string {
    return this._email;
  }
}
