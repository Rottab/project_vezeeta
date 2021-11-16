import {User} from 'src/app/user/user.model'
import {Schedule} from "./doctor/schedule/schedule.model";

export class Doctor {

  constructor(public user: User, public id: number, public category: any, public schedule: Schedule[]) {
  }

}
