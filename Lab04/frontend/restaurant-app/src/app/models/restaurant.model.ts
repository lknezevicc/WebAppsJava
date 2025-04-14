export interface Restaurant {
  id: number;
  name: string;
  address: string;
  phoneNumber: string;
  email: string;
  workingHours: { [key: string]: string }; // compatibile with JSON
  description: string;
  opened: boolean;
  averageDeliveryTime: number;
  averageCustomerRating: number;
  maxNumberOfOrders: number;
  michelinStars: number;
  workloadPercentage?: number;
}