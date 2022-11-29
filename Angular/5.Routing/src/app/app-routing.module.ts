import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { ServersComponent } from './servers/servers.component';
import { UserComponent } from './users/user/user.component';
import { EditServerComponent } from './servers/edit-server/edit-server.component';
import { ServerComponent } from './servers/server/server.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AuthGuard } from 'src/auth-guard.service';
import { CanDeactivateGuard } from './servers/edit-server/can-deactivate-guard.service';
import { ErrorPageComponent } from './error-page/error-page.component';
import { ServerResolver } from './servers/server/server-resolver.service';

//Ahhoz hogy működjön, lentebb az importsnál kellett a RouterModule.forRoot(appRoutes) rész
//Lentebb a component helyett redirectTo is adható, ami átirányít (a ** jelentése, hogy minden nem ismert route esetén)
// Ahogy react esetén itt is a végére tegyük, mert elkap mindent, ha az elején lenne mindig átirányítana
//A 11-es mappa 144-es dokumentuma (nem hosszú) hasznos lehet szükség esetén
const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'users',
    component: UsersComponent,
    children: [{ path: ':id/:name', component: UserComponent }],
  },
  {
    path: 'servers',
    // A sima canActivate esetén a parentet is levédi, míg a child verziónál csak a child routeokat
    // Megtehettük volna, hogy a canActivate-t egyesével megadjuk minden child-nál, a parentnál pedig nem, de így szebb
    // canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    component: ServersComponent,
    children: [
      {
        path: ':id',
        component: ServerComponent,
        resolve: { server: ServerResolver },
      },
      // Itt a canDeactivate jelentése, hogy a path elhagyását engedélyezze-e (a mi esetünkben pl a server módosítás esetén használjuk, mielőtt jóváhagyja a módosítást)
      // Ez a guard az edit mappájában lett létrehozva
      // Ezt is meg kell adni az app.module providers részénél
      {
        path: ':id/edit',
        component: EditServerComponent,
        canDeactivate: [CanDeactivateGuard],
      },
    ],
  },
  // { path: 'not-found', component: PageNotFoundComponent },
  // Az itt átadott message értékét ki tudjuk venni abban a componentben amelyik ezen a routeon betöltődik (most az errorpageben)
  {
    path: 'not-found',
    component: ErrorPageComponent,
    data: { message: 'Page not found' },
  },
  { path: '**', redirectTo: '/not-found' },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],

  // Ez a fentebbi apró módosítása. Akkor lehet jelentősége, ha hostolva lenne az app, és a routing nem működne megfelelően, máskülönben nem kell
  // imports: [RouterModule.forRoot(appRoutes, { useHash: true })],

  // Exportáljuk, mert az app.module.ts-ben használni akarjuk
  exports: [RouterModule],
})
export class AppRoutingModule {}
