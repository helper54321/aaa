import { Component, OnDestroy, OnInit } from '@angular/core';
import { interval, Subscription, Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit, OnDestroy {
  private firstObsSubscription: Subscription;

  constructor() {}

  // Itt létrehozunk egy observablet (ugyan itt még nem nulláról, mivel ez egy beépített, de akkoris saját)
  // Ez csak annyit csinál, hogy 1mp-néként kiírja növekvően a számokat
  // De mivel ez ha egyszer elindul magától nem áll le, tehát pl url változás esetén ugyanúgy megy tovább, sőt ahányszor elhagyjuk a componentet és újra visszatérünk egy újabb számlálót indít, de nem a másik helyére, hanem azzal párhuzamosan.
  // Így hamar rengeteg párhuzamos lehet, ami nagyon memóriaigényes. Emiatt lentebb kellett az unsubscribe, hogy elhagyás esetén szüntesse meg, és visszatérés esetén mindig egy újat indítson
  // A gyári subscribe-ok esetén csak azért nem kell az unsubscribe, mert angular elvégzi helyettünk
  ngOnInit() {
    // this.firstObsSubscription = interval(1000).subscribe((count) => {
    //   console.log(count);
    // });

    // Itt már ténylegesen a 0-ról hozunk létre egyet (ugyanazt csinálja mint a fenti)
    // Érdemes megemlíteni, hogy elképesztően ritkán (majdnem sose) szükséges saját observable létrehozása (mivel sok gyári van)
    const customIntervalObservable = Observable.create((observer) => {
      let count = 0;
      setInterval(() => {
        observer.next(count);

        // Itt megmondjuk, hogy ha ez teljesül akkor fejeződjön be
        if (count === 2) {
          observer.complete();
        }

        // Itt egy példa, ahol hibát dobunk, amit a subscribe résznél elkaphatunk
        // Ha egy hiba dobódik még az unsubscribe se muszáj, mert úgyis véget ér a végrehajtása (completed esetén ugyanez igaz)
        // Mivel fentebb a count === 2 miatt véget ér, így a jelenlegi esetben ez sose teljesülne, de mivel ez csak kreált példa, így nincs is jelentősége
        if (count > 3) {
          observer.error(new Error('Count is greater than 3'));
        }
        count++;
      }, 1000);
    });

    // Itt pedig feliratkozunk rá (nyilván máshol is lehetne), valamint a dobott hibát is elkapjuk, és a completed részt is lekezeljük
    // Itt a pipe rész teljesen opcionális, csak azt mutatja be, hogy van lehetőségünk az adat előmódosítására (tehát ebben az esetben pl 1-es számból csináltunk Round: 1-et)
    // A map pedig valószínűleg hasonlóan működik mint functionok esetén (de itt másik import), és nyilván nem csak map van, hanem jóval több
    // A pipe több paramétert is kaphat, tehát pl a map után más is elvégezhető, mint itt a filter (nyilván több értelme van előbb a filternek és utána a map, mint itt is, de lehetne fordítva is)
    this.firstObsSubscription = customIntervalObservable
      .pipe(
        filter((data) => {
          return data > 0;
        }),
        map((data: number) => {
          return 'Round: ' + (data + 1);
        })
      )
      .subscribe(
        (data) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
          alert(error);
        },
        () => {
          console.log('Completed');
        }
      );
  }

  ngOnDestroy(): void {
    this.firstObsSubscription.unsubscribe();
  }
}
