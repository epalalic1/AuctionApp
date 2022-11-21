import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  constructor(private servis:NotificationService) { }
  message = ""
  @Input()
  value!:number;


  ngOnInit(): void {
    
    /*if (this.value == 1 ){
      this.message = "Ponuda je veca";
    }
    else if (this.value == 0) {
      this.message = "Ponuda je manja";
    }*/
  }
}
