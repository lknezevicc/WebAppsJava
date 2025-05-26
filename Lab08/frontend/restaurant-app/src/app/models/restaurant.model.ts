import { WorkingHour } from "./workingHour.model";

export interface Restaurant {
  id: number;
  name: string;
  address: string;
  phoneNumber: string;
  email: string;
  workingHours: WorkingHour[];
  description: string;
  opened: boolean;
  averageDeliveryTime: number;
  averageCustomerRating: number;
  maxNumberOfOrders: number;
  michelinStars: number;
  workloadPercentage?: number;
}