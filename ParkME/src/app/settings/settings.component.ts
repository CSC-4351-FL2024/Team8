import {Component, OnInit} from '@angular/core';
import { HeaderComponent} from "../header/header.component";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {FormControl, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [HeaderComponent, MatLabel, MatFormField, MatInput, MatButton, ReactiveFormsModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css'
})
export class SettingsComponent implements OnInit {
  headerComponent: any;
  disableForm: any;
  email: undefined;
  username:undefined;

  constructor(headerComponent: HeaderComponent) {
    this.headerComponent = headerComponent;


  }

  ngOnInit(): void {
    this.disableForm = new FormControl({value: '', disabled: true})
  }

  handleSubmit() {
    console.log("Form submitted!");

  }

  setVariable() {
    this.email=this.headerComponent.email;
    this.username=this.headerComponent.username

  }
}
