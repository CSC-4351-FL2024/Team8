import { Component, Injectable, OnInit } from '@angular/core';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButton } from '@angular/material/button';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { UserDataService } from '../services/user-data.service';
import { User } from '../users';
import { UserService } from '../services/data-service.service';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [MatLabel, MatFormField, MatInput, MatButton, ReactiveFormsModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
  providers: [HeaderComponent],
})
export class SettingsComponent implements OnInit {
  disableForm: any;
  user: User | null = null;
  licensePlate = new FormControl('');
  constructor(
    private userDataService: UserDataService,
    private userService: UserService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.disableForm = new FormControl({ value: '', disabled: true });
    this.userDataService.currentUser.subscribe((user) => {
      this.user = user;
      this.licensePlate.setValue(user?.licensePlateNumber!);
    });
  }

  handleSubmit() {
    const updatedUser: User = {
      email: this.user?.email!,
      licensePlateNumber: this.licensePlate.value!.toUpperCase(),
    };
    this.userDataService.updateUser(updatedUser);
    this.userService.createUser(this.user!).subscribe({
      next: (response: any) => {
        const updatedUser: User = {
          email: response.user.email,
          bookTime: response.user.bookTime,
          parkingDeckBooked: response.user.parkingDeckBooked,
          licensePlateNumber: response.user.licensePlateNumber,
          Deckspots: response.Deckspots,
        };

        this.userDataService.updateUser(updatedUser);
        this.router.navigate(['src/app/home']);
        // Handle successful user creation (e.g., redirecting, displaying a success message)
      },
      error: () => {
        this.updateUser();
      },
    });
  }
  updateUser() {
    this.userService.updateUser(this.user!).subscribe({
      next: (response: any) => {
        const updatedUser: User = {
          email: response.user.email,
          bookTime: response.user.bookTime,
          parkingDeckBooked: response.user.parkingDeckBooked,
          licensePlateNumber: response.user.licensePlateNumber,
          Deckspots: response.Deckspots,
        };

        this.userDataService.updateUser(updatedUser);
      },
      error: (error) => {
        console.log('skill issue i guess', error);
      },
    });
  }
}
