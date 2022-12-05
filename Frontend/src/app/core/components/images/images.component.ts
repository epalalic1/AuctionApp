import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})

export class ImagesComponent implements OnInit {

  @Input()
  images: string[] = [];

  @Input()
  imageSrc: string = "";

  constructor() { }

  ngOnInit(): void {
  }

  onClick(img: string): void {
    this.imageSrc = img;
  }
}
