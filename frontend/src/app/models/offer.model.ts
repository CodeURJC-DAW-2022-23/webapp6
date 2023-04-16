import { Book } from "./book.model";
import { User } from "./user.model";

export interface Offer {
  id?: number;
  book: Book;
  seller: User;
  date: Date;
  edition: string;
  description: string;
  price: number;
  sold: boolean,
  image: boolean
}
