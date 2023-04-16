import { Book } from "./book.model";
import { User } from "./user.model";

export interface Review {
  id?: number;
  book: Book;
  author: User;
  date: Date;
  text: string
}

