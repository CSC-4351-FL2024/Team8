import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {MatButtonModule} from '@angular/material/button'
import {CommonModule} from '@angular/common'
import {SocialAuthService, GoogleSigninButtonModule, SocialUser} from '@abacritt/angularx-social-login';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLinkActive,
    RouterLink,
    MatButtonModule,
    CommonModule,
    CommonModule,
    GoogleSigninButtonModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  LoggedIn = false
  constructor( private authService:SocialAuthService) {}

  ngOnInit(): void {
    this.authService.authState.subscribe((user) => {
     this.handleLogin(user)
      console.error(user)
      //perform further logics
    });
  }
  private decodeToken(token:string){
    return JSON.parse(atob(token.split(".")[1]));
  }
  handleLogin(user:SocialUser){
if(user){
  const payload=this.decodeToken(user.idToken)
  sessionStorage.setItem("loggedInUser", JSON.stringify(payload))

}
  }

  signOut() {
    // var auth2 = gapi.auth2.getAuthInstance();
    // auth2.signOut().then(function () {
    //   console.log('User signed out.');
    // });
  }
}