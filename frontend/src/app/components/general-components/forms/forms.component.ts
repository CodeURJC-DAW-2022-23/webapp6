import { Component, Input } from '@angular/core';

@Component({
  selector: 'general-form',
  templateUrl: './general-form.component.html',
  styleUrls: ['./forms.component.css']
})
export class Form {
  @Input() text: string | undefined;
}

