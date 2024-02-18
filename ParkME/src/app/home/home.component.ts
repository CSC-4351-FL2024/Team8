import {Component, Injectable, OnInit} from '@angular/core';
declare var google:any;
@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [
  ]
})
@Injectable({
  providedIn: 'root'
})
export class HomeComponent {

  constructor() {
  }
  initMap(){
    initMap()
  }
}

  async function initMap() {
    // Request needed libraries.
    const {Map} = await google.maps.importLibrary("maps");
    const {AdvancedMarkerElement} = await google.maps.importLibrary("marker");
    const map = new Map(document.getElementById("map")!, {
      center: {lat: 33.747893689359636, lng: -84.38741045065618},
      zoom: 14,
      mapId: "4504f8b37365c3d0",
    });

    // Array of marker positions and names
    const markers = [
      {position: {lat: 33.75557670762253, lng: -84.38698213132827}, name: "T Deck"},
      {position: {lat: 33.75135768192294, lng: -84.3840576168146}, name: "K Deck"},
      {position: {lat: 33.75334683794284, lng: -84.38414303503839}, name: "M Deck"},
      {position: {lat: 33.751414302259235, lng: -84.38442667367643}, name: "N Deck"},
      {position: {lat: 33.7517392377102, lng: -84.38351822583688}, name: "S Deck"},
      {position: {lat: 33.741606751431206, lng: -84.3902396173013}, name: "Blue Lot"},
      {position: {lat: 33.73920445683548, lng: -84.39107924418565}, name: "Green Lot"}
      // Add more markers with positions and names as needed
    ];

    function generateInfo(markerName: string) {
      switch (markerName) {
        case "T Deck":
          return "Spaces Available: 15";
        case "K Deck":
          return "Spaces Available: 15";
        case "M Deck":
          return "Spaces Available: 15";
        case "N Deck":
          return "Spaces Available: 15";
        case "S Deck":
          return "Spaces Available: 15";
        case "Blue Lot":
          return "Spaces Available: 15";
        case "Green Lot":
          return "Spaces Available: 15";
        default:
          return "Info not available";
      }
    }

    let openInfoWindow: { close: () => void; } | null = null; // Variable to store the currently open info window

    markers.forEach(marker => {
      const decks = document.createElement("div");
      decks.className = "decks";
      decks.textContent = `${marker.name}`;

      const advancedMarker = new AdvancedMarkerElement({
        map,
        position: marker.position,
        content: decks,
      });

      const infoWindow = new google.maps.InfoWindow({
        content: `<h2>${marker.name}</h2><p>${generateInfo(marker.name)}</p>`
      });

      // Open info window when marker is clicked
      advancedMarker.addListener('click', function () {
        // Close the previously opened info window, if any
        if (openInfoWindow) {
          openInfoWindow.close();
        }
        // Open the current marker's info window
        infoWindow.open(map, advancedMarker);
        // Update the currently open info window variable
        openInfoWindow = infoWindow;
      });
    });
  }


