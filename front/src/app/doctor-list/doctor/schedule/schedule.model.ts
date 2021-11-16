export class Schedule {
  constructor(public clinic: { id: number, name: string }, public id: number, public timeFrom: Date, public timeTo: Date, public dayOfWeek: string) {
  }
}
