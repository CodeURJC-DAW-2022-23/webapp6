import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css'],
})
export class ContactComponent {
  latitude: number = 51.5055676;
  longitude: number = -0.1326702;

  markers: marker[] = [
    {
      latitude: 51.5055676,
      longitude: -0.1326702,
      label: 'A',
    },
  ];
}

interface marker {
  latitude: number;
  longitude: number;
  label?: string;
}
