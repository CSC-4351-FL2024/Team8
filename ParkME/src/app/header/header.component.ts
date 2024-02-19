import {Component, Injectable, OnInit} from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {MatButtonModule} from '@angular/material/button'
import {CommonModule, NgOptimizedImage} from '@angular/common'
import {SocialAuthService, GoogleSigninButtonModule, SocialUser} from '@abacritt/angularx-social-login';
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";
declare var google:any;
import { Buffer } from 'buffer';

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
    MatMenuItem

  ],





  templateUrl: './header.component.html',
  styleUrl: './header.component.css'

})
@Injectable({providedIn:"root"})
export class HeaderComponent implements OnInit{

  LoggedIn = false
  userProfilePicture: undefined;
  email: undefined;
  username:undefined;

  constructor(private authService: SocialAuthService) {
  }

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
      this.handleLogin(user)
      console.log(user)
    });
  }

  private decodeToken(token: string) {
    // return JSON.parse(atob(token.split(".")[1]));

      return JSON.parse(Buffer.from(token.split('.')[1], 'base64').toString());
  }

  handleLogin(user: SocialUser) {
    if (user) {

      const payload = this.decodeToken(user.idToken)
      console.log(payload)
      sessionStorage.setItem("loggedInUser", JSON.stringify(payload));
      this.setVariable();

    }
  }

  setVariable() {
    this.userProfilePicture = JSON.parse(sessionStorage.getItem('loggedInUser')!).picture;
    this.LoggedIn = true
    this.email = JSON.parse(sessionStorage.getItem('loggedInUser')!).email;
    this.username=JSON.parse(sessionStorage.getItem('loggedInUser')!).username;
  }

    signOut() {
    google.accounts.id.disableAutoSelect();
    this.LoggedIn = false;
    sessionStorage.clear();
  }
}
