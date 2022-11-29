import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data, Params, Router } from '@angular/router';

import { ServersService } from '../servers.service';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css'],
})
export class ServerComponent implements OnInit {
  server: { id: number; name: string; status: string };

  constructor(
    private serversService: ServersService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    // Kinyerjük az id-t az url-ből, és aszerint kérünk ki a serviceből
    //Viszont a paraméter stringként tárolja, nekünk viszont numberként kell, ezért a + jellel konvertáltuk (lentebb a subscribe résznél szintén)
    // Ez a megoldás is teljesen ok volt, de később lecseréltük a lentebbi alternatívára

    // const id = +this.route.snapshot.queryParams['id'];
    // this.server = this.serversService.getServer(id);
    // this.route.params.subscribe((params: Params) => {
    //   this.server = this.serversService.getServer(+params['id']);
    // })

    this.route.data.subscribe((data: Data) => {
      // A data fogja tartalmazni azt az adatot, amit a routing fájlban a resolve résznél megadunk (ott értékként a ServerResolver-t adtuk meg, ami azt jelenti, hogy az abban lévő kód lefut, és amit az visszaad, azt tartalmazza itt a data)
      this.server = data['server'];
    });
  }

  onEdit() {
    // Itt a queryParamsHandling: 'preserve' jelentése, hogy átirányítás esetén a queryParamétereket is fűzze hozzá a linkhez elirányítás esetén, ne veszítsük el
    // Ha itt is akarnánk hozzáadni, akkor preserve helyett merge kellene, hogy összefésülje, és ne felülírja
    this.router.navigate(['edit'], {
      relativeTo: this.route,
      queryParamsHandling: 'preserve',
    });
  }
}
