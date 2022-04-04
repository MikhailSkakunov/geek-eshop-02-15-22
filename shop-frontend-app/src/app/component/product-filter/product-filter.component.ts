import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-product-filter',
  templateUrl: './product-filter.component.html',
  styleUrls: ['./product-filter.component.scss']
})
export class ProductFilterComponent implements OnInit {

  nameFilter: string = '';

  @Output() searchNameEvent = new EventEmitter<string>();

  constructor() { }

  applyNameFilter() {
    this.searchNameEvent.emit(this.nameFilter);
  }

  ngOnInit(): void {
  }

}
