import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'common',
  templateUrl: './common.component.html',
  styleUrls: ['./common.component.css']
})
export class CommonComponent {
  constructor(private router: Router) { }
}
