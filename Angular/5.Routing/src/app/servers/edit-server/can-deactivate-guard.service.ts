import {
  ActivatedRouteSnapshot,
  CanDeactivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

export interface CanComponentDeactivate {
  canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

export class CanDeactivateGuard
  implements CanDeactivate<CanComponentDeactivate>
{
  // Egy route elhagyása esetén fog lefutni (a mi esetünkben pl a server módosítás esetén használjuk, mielőtt jóváhagyja a módosítást)
  // Itt a component résszel a fenti interfacet adtuk meg, tehát egy olyan class kell, ami implementálja azt az interfacet, ezáltal lesz egy canDeactivate metódusa (mivel ugye kötelező implementálnia)
  // A paraméterek végén a ? miatt itt a nextState? jelentése, hogy opcionális, nem kötelező
  canDeactivate(
    component: CanComponentDeactivate,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    return component.canDeactivate();
  }
}
