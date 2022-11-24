import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';
import { LastChanceService } from '../../services/last-chance.service';

@Component({
  selector: 'app-last-chance',
  templateUrl: './last-chance.component.html',
  styleUrls: ['./last-chance.component.css']
})

export class LastChanceComponent implements OnInit {

  lastChanceProducts!: Product[];

  usersRole: number = 0;

  constructor(
    private lastChanceService: LastChanceService,
    private bidService: BidService,
    private initializeService: InitializeService) { }


  ngOnInit(): void {
    this.lastChanceProducts = this.lastChanceService.getLastChanceProducts();
    this.bidService.getBids();
    this.apiService.getUsers().subscribe((res) => {
    })
  }
}

