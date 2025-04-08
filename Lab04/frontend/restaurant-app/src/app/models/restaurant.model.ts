import { DayOfWeek } from "../enums/day-of-week.enum";

export interface Restaurant {
  id: number;
  name: string;
  address: string;
  phoneNumber: string;
  email: string;
  workingHours: Map<DayOfWeek, string>;
  description: string;
  opened: boolean;
  averageDeliveryTime: number;
  averageCustomerRating: number;
  maxNumberOfOrders: number;
  michelinStars: number;
}