import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input()
  user: User = new User();

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {}

}
