import { Routes } from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {HomeComponent} from "./home/home.component";
import {SettingsComponent} from "./settings/settings.component";

export const routes: Routes = [
  {path:'',
    redirectTo:'src/app/home',
    pathMatch: "full"
  },
  {path:'src/app/home', component: HomeComponent},
  {path:'src/app/settings', component: SettingsComponent}
];
