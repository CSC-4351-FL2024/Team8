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
              '754588958744-8l1p80q3rr35vj0ccbk7vsvk6ep9iag9.apps.googleusercontent.com'
            )
          }
        ],
        onError: (error) => {
          console.error(error);
        }
      } as SocialAuthServiceConfig
    }, provideAnimationsAsync()]}





