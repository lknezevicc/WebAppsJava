import { DayOfWeek } from "../enums/day-of-week.enum";

export interface WorkingHour {
  dayOfWeek: DayOfWeek;
  workingHour: string;
}