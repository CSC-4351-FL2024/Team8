import { Component, Injectable, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
declare var google: any;
import { UserService } from '../services/data-service.service';
import { UserDataService } from '../services/user-data.service';
import { User } from '../users';
import { response } from 'express';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import { GoogleSigninButtonModule } from '@abacritt/angularx-social-login';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [CommonModule, NgOptimizedImage, GoogleSigninButtonModule],
})
@Injectable({
  providedIn: 'root',
})
export class HomeComponent implements OnInit {
  map: any;
  user: User | null = null;
  DeckMspot: number = 15;
  DeckTspot: number = 15;
  DeckNspot: number = 15;
  DeckGspot: number = 15;
  DeckBspot: number = 15;
  DeckSspot: number = 15;
  constructor(
    private snackBar: MatSnackBar,
    private userService: UserService,
    private userDataService: UserDataService,
  ) {}
  setSpots() {
    if (this.user?.Deckspots?.length == 6) {
      this.DeckMspot = this.DeckMspot! - this.user?.Deckspots[0]!;
      this.DeckTspot = this.DeckTspot! - this.user?.Deckspots[1]!;
      this.DeckNspot = this.DeckNspot! - this.user?.Deckspots[2]!;
      this.DeckSspot = this.DeckSspot! - this.user?.Deckspots[3]!;
      this.DeckBspot = this.DeckBspot! - this.user?.Deckspots[4]!;
      this.DeckGspot = this.DeckGspot! - this.user?.Deckspots[5]!;
      this.setMarker();
    }
  }
  ngOnInit(): void {
    this.initMap();
    this.userDataService.currentUser.subscribe((user) => {
      this.user = user;
      this.DeckBspot = 15;
      this.DeckGspot = 15;
      this.DeckMspot = 15;
      this.DeckNspot = 15;
      this.DeckTspot = 15;
      this.DeckSspot = 15;
      this.setSpots(); // Ensure setSpots is called after the user is set
    });
  }

  async initMap() {
    const { Map } = await google.maps.importLibrary('maps');
    this.map = new Map(document.getElementById('map')!, {
      center: { lat: 33.747893689359636, lng: -84.38741045065618 },
      zoom: 15.4,
      mapId: '4504f8b37365c3d0',
    });
  }
  setMarker() {
    const markers = [
      {
        position: { lat: 33.75557670762253, lng: -84.38698213132827 },
        name: 'T Deck',
        spots: this.DeckTspot,
      },
      {
        position: { lat: 33.75334683794284, lng: -84.38414303503839 },
        name: 'M Deck',
        spots: this.DeckMspot,
      },
      {
        position: { lat: 33.751414302259235, lng: -84.38442667367643 },
        name: 'N Deck',
        spots: this.DeckNspot,
      },
      {
        position: { lat: 33.7517392377102, lng: -84.38351822583688 },
        name: 'S Deck',
        spots: this.DeckSspot,
      },
      {
        position: { lat: 33.741606751431206, lng: -84.3902396173013 },
        name: 'Blue Lot',
        spots: this.DeckBspot,
      },
      {
        position: { lat: 33.73920445683548, lng: -84.39107924418565 },
        name: 'Green Lot',
        spots: this.DeckGspot,
      },
    ];

    markers.forEach((marker, i) => {
      const newMarker = new google.maps.Marker({
        position: marker.position,
        map: this.map,
        title: `${i + 1}. ${marker.name}`,
        label: `${marker.spots} ${marker.name}`,
        icon: {
          url: '../../assets/bubble.png',
          scaledSize: new google.maps.Size(100, 50),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(20, 40),
        },
      });

      const infoWindow = new google.maps.InfoWindow({
        content: `<h2>${marker.name}</h2><p>Available spots: ${marker.spots}</p>`,
      });

      newMarker.addListener('click', () => {
        infoWindow.open(this.map, newMarker);
      });
    });
  }

  checkoutButtonClicked(): void {
    this.userService.checkoutParkingDeck(this.user!).subscribe({
      next: (response: any) => {
        const updatedUser: User = {
          email: response.user.email,
          bookTime: response.user.bookTime,
          parkingDeckBooked: response.user.parkingDeckBooked,
          licensePlateNumber: response.user.licensePlateNumber,
          Deckspots: response.deckSpots,
        };

        this.userDataService.updateUser(updatedUser);
      },
      error: (error) => {
        console.error('Failed', error);
        // Handle error, maybe show an error message
      },
    });
  }

  reserveButtonClicked(deck: string): void {
    this.userService.reserveParkingDeck(this.user!, deck).subscribe({
      next: (response: any) => {
        const updatedUser: User = {
          email: response.user.email,
          bookTime: response.user.bookTime,
          parkingDeckBooked: response.user.parkingDeckBooked,
          licensePlateNumber: response.user.licensePlateNumber,
          Deckspots: response.deckSpots,
        };

        this.userDataService.updateUser(updatedUser);
      },
      error: (error) => {
        console.error('Deck full', error);
        // Handle error, maybe show an error message
      },
    });
  }
  checkUserStatus(): boolean {
    if (this.user) {
      return true;
    }
    return false;
  }
  checkUserBookedStatus(): boolean {
    if (this.user?.parkingDeckBooked) {
      return true;
    }
    return false;
  }
  checkUserBookedDeckT(): boolean {
    if (this.user?.parkingDeckBooked == 't') {
      return true;
    }
    return false;
  }
  checkUserBookedDeckM(): boolean {
    if (this.user?.parkingDeckBooked == 'm') {
      return true;
    }
    return false;
  }
  checkUserBookedDeckN(): boolean {
    if (this.user?.parkingDeckBooked == 'n') {
      return true;
    }
    return false;
  }
  checkUserBookedDeckS(): boolean {
    if (this.user?.parkingDeckBooked == 's') {
      return true;
    }
    return false;
  }
  checkUserBookedDeckB(): boolean {
    if (this.user?.parkingDeckBooked == 'b') {
      return true;
    }
    return false;
  }
  checkUserBookedDeckG(): boolean {
    if (this.user?.parkingDeckBooked == 'g') {
      return true;
    }
    return false;
  }
}
