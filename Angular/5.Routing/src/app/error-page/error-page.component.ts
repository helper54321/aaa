import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data } from '@angular/router';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css'],
})
export class ErrorPageComponent implements OnInit {
  errorMessage: string;

  constructor(private route: ActivatedRoute) {}

  // Itt a data által kivett érték az amit a routing fájlban átadunk értékként az ehhez a componenthez tartozó résznél, azt kapja meg itt
  ngOnInit(): void {
    // this.errorMessage = this.route.snapshot.data['message'];

    // Itt feliratkozunk rá, mert az értéke akár akkor is változhat, amikor ezen az oldalon vagyunk
    // Ez a rész csak akkor szükséges, ha van rá esély, hogy változzon, anélkül a fentebbi kikommentelt is ok lenne (a snapshotos)
    this.route.data.subscribe((data: Data) => {
      this.errorMessage = data['message'];
    });
  }
}
