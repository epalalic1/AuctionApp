import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ChildActivationStart } from '@angular/router';
import { Validation } from 'src/app/core/utils/validation';

@Component({
  selector: 'app-date-picker-birthday',
  templateUrl: './date-picker-birthday.component.html',
  styleUrls: ['./date-picker-birthday.component.css']
})
export class DatePickerBirthdayComponent implements OnInit {

  days = ['01', '02', '03', '04', '05', '06', '07', '09', '10',
    '11', '12', '13', '14', '15', '16', '17', '19', '20',
    '21', '22', '23', '24', '25', '26', '27', '29', '30', '31'];

  months = ['Janury', 'February ', 'March', 'April', 'May', 'June', 'July', 'August', 'September',
    'October', ' November', 'December']

  years = ['1940', '1941', '1942', '1943', '1944', '1945', '1946', '1947', '1948', '1949', '1950',
    '1951', '1952', '1953', '1954', '1955', '1956', '1957', '1958', '1959', '1960',
    '1961', '1962', '1963', '1964', '1965', '1966', '1967', '1968', '1969', '1970',
    '1971', '1972', '1973', '1974', '1975', '1976', '1977', '1978', '1979', '1980',
    '1981', '1982', '1983', '1984', '1985', '1986', '1987', '1988', '1989', '1990',
    '1991', '1992', '1993', '1994', '1995', '1996', '1997', '1998', '1999', '2000',
    '2001', '2002', '2003', '2004', '2005']

  day: string = "";

  month!: number;

  year: string = "";

  valid!: boolean;

  constructor() { }

  ngOnInit(): void {
  }

  onClickDay(day: string) {
    this.day = day.replace(/^0+/, '');
    let  button = <HTMLInputElement>document.getElementById('dayBtn');
    button.innerHTML = day;
    this.valid = Validation.validateBirhtday(this.day, this.month, this.year);
  }

  onClickMonth(month: number) {
    this.month = month + 1;
    let  button = <HTMLInputElement>document.getElementById('monthBtn');
    button.innerHTML = this.months[month];
    this.valid = Validation.validateBirhtday(this.day, this.month, this.year);
  }

  onClickYear(year: string) {
    this.year = year;
    let  button = <HTMLInputElement>document.getElementById('yearBtn');
    button.innerHTML = year;
    this.valid = Validation.validateBirhtday(this.day, this.month, this.year);
  }

}
