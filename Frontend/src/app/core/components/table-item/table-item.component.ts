import { Component, Input, OnInit } from '@angular/core';
import { ItemInTable } from '../../models/item-in-table';

@Component({
  selector: 'app-table-item',
  templateUrl: './table-item.component.html',
  styleUrls: ['./table-item.component.css']
})
export class TableItemComponent implements OnInit {

  @Input()
  item?:ItemInTable;

  constructor() { }

  ngOnInit(): void {
    console.log(this.item);
  }

}
