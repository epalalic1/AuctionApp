import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';

@Component({
  selector: 'app-last-chance',
  templateUrl: './last-chance.component.html',
  styleUrls: ['./last-chance.component.css']
})

export class LastChanceComponent implements OnInit {

  lastChanceProducts!: Product[];

  usersRole: number = 0;

  constructor(private bidService: BidService,
    private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getLastChanceProduct().subscribe((rez)=>{
      this.lastChanceProducts = <Product[]> JSON.parse(JSON.stringify(rez));
    })
    this.bidService.getBids();
    this.apiService.getUsers().subscribe((res) => {
    })
  }
}

