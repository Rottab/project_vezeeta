import {User} from "../user/user.model";

export class Appointment {
  constructor(public id: number,
              public date: Date,
              public doctor: { user: { firstName: string, lastName: string }, category: { name: string } },
              public clinic: { name: string },
              public approved: boolean,
              public user: User,
  ) {
  }
}
