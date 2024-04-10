import { Component, Injectable, OnInit } from '@angular/core';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButton } from '@angular/material/button';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { UserDataService } from '../services/user-data.service';
import { User } from '../users';
import { UserService } from '../services/user-service.service';

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
  ) {}

  ngOnInit(): void {
    this.userDataService.currentUser.subscribe((user) => (this.user = user));
    this.disableForm = new FormControl({ value: '', disabled: true });
  }

  handleSubmit() {
    const updatedUser: User = {
      email: this.user?.email!,
      userId: this.user?.userId!,
      licensePlateNumber: this.licensePlate.value!,
    };
    this.userDataService.updateUser(updatedUser);
    console.log(this.licensePlate.value);
    console.log(this.user?.licensePlateNumber);
    this.userService.createUser(this.user!).subscribe({
      next: (user) => {
        console.log('User created successfully', user);
        // Handle successful user creation (e.g., redirecting, displaying a success message)
      },
      error: () => {
        this.updateUser();
      },
    });
  }
  updateUser() {
    this.userService.updateUser(this.user!).subscribe({
      next: (user) => {
        console.log('User created successfully', user);
      },
      error: (error) => {
        console.log('skill issue i guess', error);
      },
    });
  }
}
