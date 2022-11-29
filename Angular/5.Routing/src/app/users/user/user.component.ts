import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit, OnDestroy {
  user: { id: number; name: string };
  paramsSubscription: Subscription

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    // Így tudjuk kinyerni a dinamikus paramétert, tehát pl a /user/1/valami-ből
    this.user = {
      id: this.route.snapshot.params['id'],
      name: this.route.snapshot.params['name'],
    };

    // Ez egy observable, amire feliratkozunk, és figyelje ha változás történik a paraméterben
    //Tehát ez a kód nem fog lefutni, csak akkor ha ténylegesen változás történik abban amire feliratkozik
    //Ez pl azért lehet szükséges, mert ha már a /user-en vagyunk (pl /user/1/valami) és egy button hatására (tehát nem közvetlen beírással) megváltoztatjuk az értékeket, attól nem fogja újratölteni a componentet mert már alapból azon vagyunk (hiába változott az értéke)
    //Viszont így a feliratkozással már figyelni fogja hogy változik-e
    //Ha olyan eset van, amikor a componenten belül nem változtatjuk a paraméter értékét elirányítás nélkül (csak kívülről), akkor ez nem szükséges
    this.paramsSubscription = this.route.params.subscribe((params: Params) => {
      this.user.id = params['id'];
      this.user.name = params['name'];
    });
  }

  ngOnDestroy(): void {
    // Csak hogy leiratkozzunk ha elhagyjuk a componentet (csak hatékonysági okból van jelentősége, enélkül is működne)
    // Ennél nem is lenne szükség rá, angular megteszi a háttérben. De saját observables esetén szükséges
    this.paramsSubscription.unsubscribe()
  }
}
