import { Component, OnInit } from '@angular/core';
import { AccountsService } from './accounts.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

  // Ha központi tárolóként akarjuk használni (mint itt is), akkor csak a legfelsőbb szinten adjuk meg, ahol még hozzáférést akarunk hozzá
  //Ez lehetővé teszi, hogy ez a component és minden childja hozzáférjen
  //Viszont ha egy child külön feltünteti még1x, akkor saját tárolót hoz létre, és felülírja amit a parenttől kapna!!!!!!!!!!!!!!!
  //Viszont ettől függetlenül a konstruktoroknál ugyanúgy meg kell adni a child componentek esetén is
  //Néha lehet hogy mindig külön tárolót akarunk, de legtöbbször nem ez az eset!!!
  //Később kikommenteltük, de csak azért mert még ennél is van egyel magasabb szint: az app.module.ts (tehát oda lett áttéve)
  //Még egy további előnye az átvitelnek, hogy így már a service-be is lehet service-t injektálni (de csak akkor ha az app.module.ts-ben van megadva)

  // providers: [AccountsService],
})
export class AppComponent implements OnInit {
  // Kivezettük az accounts.service.ts-be

  // accounts = [
  //   {
  //     name: 'Master Account',
  //     status: 'active'
  //   },
  //   {
  //     name: 'Testaccount',
  //     status: 'inactive'
  //   },
  //   {
  //     name: 'Hidden Account',
  //     status: 'unknown'
  //   }
  // ];

  accounts: { name: string; status: string }[] = [];

  constructor(private accountsService: AccountsService) {}

  ngOnInit(): void {
    this.accounts = this.accountsService.accounts;
  }

  //Ki lettek vezetve a servicebe

  // onAccountAdded(newAccount: {name: string, status: string}) {
  //   this.accounts.push(newAccount);
  // }

  // onStatusChanged(updateInfo: {id: number, newStatus: string}) {
  //   this.accounts[updateInfo.id].status = updateInfo.newStatus;
  // }
}
