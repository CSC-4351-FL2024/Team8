import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { SocialAuthServiceConfig } from '@abacritt/angularx-social-login';
import {
  GoogleLoginProvider,
} from '@abacritt/angularx-social-login';
import {provideAnimations} from "@angular/platform-browser/animations";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

export const appConfig: ApplicationConfig = {

  providers: [provideRouter(routes),provideAnimations() ,provideClientHydration(),
    {


      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
            '15399289946-7qg6n491t7iho4smmgg5jv6rj23fen7r.apps.googleusercontent.com'
              )
          }
        ],
        imports:[],
        onError: (error) => {
          console.error(error);
        }
      } as SocialAuthServiceConfig
    }, provideAnimationsAsync()]}





