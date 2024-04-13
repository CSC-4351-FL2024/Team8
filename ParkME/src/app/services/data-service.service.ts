import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../users'; // Adjust the path as necessary

const baseUrl = 'http://localhost:8080/api/users'; // URL to your Spring Boot application

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${baseUrl}/`, user);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${baseUrl}/${user.email}`, user);
  }

  reserveParkingDeck(user: User, parkingDeck: string): Observable<User> {
    return this.http.put<User>(`${baseUrl}/${user.email}/reserve`, parkingDeck);
  }

  getUserById(userEmail: String): Observable<any> {
    return this.http.get(`${baseUrl}/${userEmail}`);
  }

  checkoutParkingDeck(user: User): Observable<User> {
    return this.http.delete<User>(
      `${baseUrl}/${user.email}/checkout`);
  }
  //createUser(user: User): Observable<User> {
  // return this.http.post<User>(baseUrl, user); // Removed the trailing slash
  //}
}
