import { Component, Injectable, OnInit } from '@angular/core';

declare var google: any;

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: []
})
@Injectable({
  providedIn: 'root'
})
export class HomeComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
    this.initMap();
  }

  async initMap() {
    const { Map } = await google.maps.importLibrary("maps");

    const map = new Map(document.getElementById("map")!, {
      center: { lat: 33.747893689359636, lng: -84.38741045065618 },
      zoom: 15.4,
      mapId: "4504f8b37365c3d0"
    });

    const INITIAL_AVAILABLE_SPOTS = 15;

    const markers = [
      { position: { lat: 33.75557670762253, lng: -84.38698213132827 }, name: "T Deck", spots: INITIAL_AVAILABLE_SPOTS},
      { position: { lat: 33.75334683794284, lng: -84.38414303503839 }, name: "M Deck", spots: INITIAL_AVAILABLE_SPOTS },
      { position: { lat: 33.751414302259235, lng: -84.38442667367643 }, name: "N Deck", spots: INITIAL_AVAILABLE_SPOTS },
      { position: { lat: 33.7517392377102, lng: -84.38351822583688 }, name: "S Deck", spots: INITIAL_AVAILABLE_SPOTS },
      { position: { lat: 33.741606751431206, lng: -84.3902396173013 }, name: "Blue Lot", spots: INITIAL_AVAILABLE_SPOTS },
      { position: { lat: 33.73920445683548, lng: -84.39107924418565 }, name: "Green Lot", spots: INITIAL_AVAILABLE_SPOTS }
    ];

    markers.forEach((marker, i) => {
      const newMarker = new google.maps.Marker({
        position: marker.position,
        map: map,
        title: `${i + 1}. ${marker.name}`,
        label: `${marker.spots} ${marker.name}`,
        icon: {
          url: '../../assets/bubble.png', 
          scaledSize: new google.maps.Size(100, 50), 
          origin: new google.maps.Point(0, 0), 
          anchor: new google.maps.Point(20, 40)
        }
      });

      const infoWindow = new google.maps.InfoWindow({
        content: `<h2>${marker.name}</h2><p>Available spots: ${marker.spots}</p>`
      });

      newMarker.addListener("click", () => {
        infoWindow.open(map, newMarker);
      });
    });
  }
}
