import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../users'; // Adjust the import path as necessary

@Injectable({
  providedIn: 'root',
})
export class UserDataService {
  // Use BehaviorSubject to hold a User object
  private userSource = new BehaviorSubject<User | null>(null);

  currentUser = this.userSource.asObservable();

  constructor() {}

  updateUser(userData: User) {
    this.userSource.next(userData);
  }
}
