import { Component, Injectable, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import {
  SocialAuthService,
  GoogleSigninButtonModule,
  SocialUser,
} from '@abacritt/angularx-social-login';
import { MatMenu, MatMenuItem, MatMenuTrigger } from '@angular/material/menu';
declare var google: any;
import { Buffer } from 'buffer';
import { User } from '../users';
import { UserDataService } from '../services/user-data.service';
import { UserService } from '../services/user-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLinkActive,
    RouterLink,
    MatButtonModule,
    CommonModule,
    GoogleSigninButtonModule,
    MatMenuTrigger,
    MatMenu,
    NgOptimizedImage,
    MatMenuItem,
  ],

  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
@Injectable({ providedIn: 'root' })
export class HeaderComponent implements OnInit {
  user: User | null = null;
  LoggedIn = false;
  userProfilePicture: undefined;
  public email: string = '';
  public username: string = '';

  constructor(
    private authService: SocialAuthService,
    private userDataService: UserDataService,
    private userService: UserService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      this.handleLogin(user);
      // console.log(user);
    });
    this.userDataService.currentUser.subscribe((user) => (this.user = user));
  }

  private decodeToken(token: string) {
    // return JSON.parse(atob(token.split(".")[1]));

    return JSON.parse(Buffer.from(token.split('.')[1], 'base64').toString());
  }

  handleLogin(user: SocialUser) {
    if (user) {
      const payload = this.decodeToken(user.idToken);
      sessionStorage.setItem('loggedInUser', JSON.stringify(payload));
      this.setVariable(payload, user.id);
    }
  }

  setVariable(payload: any, Id: any) {
    this.userProfilePicture = JSON.parse(
      sessionStorage.getItem('loggedInUser')!,
    ).picture;
    // console.log(this.userProfilePicture)
    this.LoggedIn = true;
    this.email = JSON.parse(sessionStorage.getItem('loggedInUser')!).email;
    // console.log(this.email)
    this.username = JSON.parse(sessionStorage.getItem('loggedInUser')!).name;
    // console.log(this.username)
    this.userService.getUserById(Id).subscribe({
      next: (response) => {
        console.log(response);
        // Handle successful user creation (e.g., redirecting, displaying a success message)
      },
      error: () => {
        const updatedUser: User = {
          email: payload.email,
          userId: Id,
        };
        this.userDataService.updateUser(updatedUser);
        this.router.navigate(['/settings']);
      },
    });
  }

  signOut() {
    google.accounts.id.disableAutoSelect();
    this.LoggedIn = false;
    sessionStorage.clear();
  }
}
