import {Component, Injectable, OnInit} from '@angular/core';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {FormControl, ReactiveFormsModule} from "@angular/forms";
@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [ MatLabel, MatFormField, MatInput, MatButton, ReactiveFormsModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css'
})
export class SettingsComponent implements OnInit {
  disableForm: any;
  email: undefined;
  username:undefined;

  constructor() {


  }

  ngOnInit(): void {
    this.disableForm = new FormControl({value: '', disabled: true})
  }

  handleSubmit() {
    console.log("Form submitted!");

  }

  setVariable(email:any, username:any) {
    this.email=email;
    this.username=username
    console.log(this.email)
    console.log(this.username)
    this.ngOnInit();

  }
}
