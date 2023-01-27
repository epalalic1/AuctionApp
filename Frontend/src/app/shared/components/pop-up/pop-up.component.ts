import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-pop-up-component',
  templateUrl: './pop-up.component.html',
  styleUrls: ['./pop-up.component.css']
})
export class PopUpComponent implements OnInit {

  hide!:boolean;

  constructor(private matDialogRef: MatDialogRef<PopUpComponent>,@Inject(MAT_DIALOG_DATA) public data :any) {
    this.hide = data.disabled
    matDialogRef.disableClose = true;
   }

  ngOnInit(): void {
  }

}
