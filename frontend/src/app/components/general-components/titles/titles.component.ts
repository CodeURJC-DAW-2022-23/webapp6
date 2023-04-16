import { Component, Input } from '@angular/core';

@Component({
  selector: 'general-title',
  templateUrl: './general-title.component.html',
  styleUrls: ['./titles.component.css']
})
export class GeneralTitle {
  @Input() title: string | undefined;
}

@Component({
  selector: 'section-title',
  templateUrl: './section-title.component.html',
  styleUrls: ['./titles.component.css']
})
export class SectionTitle {
  @Input() title: string | undefined;
}
