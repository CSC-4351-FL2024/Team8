export interface User {
  email: String;
  parkingDeckBooked?: String;
  bookTime?: String; // Use appropriate type or format as string
  licensePlateNumber?: string;
  Deckspots?: Array<number>;
}
