import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'general-form',
  templateUrl: './general-form.component.html',
  styleUrls: ['./forms.component.css']
})
export class Form {
  inputText = '';

  @Input() text: string | undefined;
  @Input() type: string | undefined;

  @Output() submitForm: EventEmitter<string> = new EventEmitter<string>();

  onSubmit() {
    this.submitForm.emit(this.inputText);
  }

}
