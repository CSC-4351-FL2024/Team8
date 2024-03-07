import {Component, Injectable, OnInit} from '@angular/core';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {HeaderComponent} from "../header/header.component";

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [ MatLabel, MatFormField, MatInput, MatButton, ReactiveFormsModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
  providers:[HeaderComponent]
})
export class SettingsComponent implements OnInit {
  disableForm: any;



  constructor(public headerComponent:HeaderComponent) {


  }

  ngOnInit(): void {
    this.disableForm = new FormControl({value: '', disabled: true})

  }

  handleSubmit() {
    console.log("Form submitted!");
    console.log(this.headerComponent.email)
    console.log(this.headerComponent.username)

  }

}
