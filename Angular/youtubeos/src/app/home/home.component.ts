import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  headerName = 'Angular tutorial';
  salary = 1000;
  isDisabled = false;
  colorName = 'red';
  font = "40px"

  // css-ben lévő class
  className="headclass"

  styleValue={"color": "yellow", "font-size": "60px"}

  constructor() {}

  ngOnInit(): void {}

  functionClick(name: string) {
    alert(name);
  }
}
