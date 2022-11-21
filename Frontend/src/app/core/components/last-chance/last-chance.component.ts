import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';

@Component({
  selector: 'app-last-chance',
  templateUrl: './last-chance.component.html',
  styleUrls: ['./last-chance.component.css']
})
export class LastChanceComponent implements OnInit {

  lastChanceProducts!: Product[];
  usersRole : number = 0;

  constructor(private servis:LastChanceService,private serivis1:BidService,
    private servisI:InitializeService) { }

  ngOnInit(): void {
    this.lastChanceProducts = this.servis.getLastChanceProducts();
    this.serivis1.getBids();
    this.servisI.getUsers().subscribe((rez) =>{
    })

}
