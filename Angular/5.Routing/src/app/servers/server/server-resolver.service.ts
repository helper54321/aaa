import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { ServersService } from '../servers.service';

interface Server {
  id: number;
  name: string;
  status: string;
}

@Injectable()

// Az app.moduleban a providersnél meg kell adni, míg a router fájlban pedig azért, hogy használjuk (ott ahol akarjuk) (a resolve résznél adtuk meg)
// Itt a resolve<> között a fentebbi interfacenk van (mivel egynél több helyen adjuk meg azt a formát, így kivittük kintre)
export class ServerResolver implements Resolve<Server> {
  constructor(private serversService: ServersService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Server> | Promise<Server> | Server {
    // Itt kivesszük a route paramétert, mivel azt meg kell adnunk a function paramétereként (de a + jel miatt konvertálva adjuk át)
    // Ez lényegében egy alternatíva a componentben lévő oninit esetére (ott is lehet, ahogy korábban csináltuk)
    // Ez egy componenttel ellentétben minden route változás esetén lefut
    return this.serversService.getServer(+route.params['id']);
  }
}
