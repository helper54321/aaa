import { Component } from '@angular/core';
import { AccountsService } from '../accounts.service';
import { LoggingService } from '../logging.service';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css'],

  //Ez is kötelezően kellett, hogy működjön a dependency injection
  // providers: [LoggingService],
})
export class NewAccountComponent {
  //A private megadása előtte csak azért kell, mert így kettős szerepe van a paraméternek:
  //Egyrészt ugye átadja paraméterként, ez nem újdonság. Másrészt viszont készít belőle egy osztályváltozót is. Tehát lényegében csak egy shortcut (2 külön lépés 1-el)
  // Itt történik a dependency injection (a háttérben lényegében példányosítja a LoggingService-t)
  //Viszont ahhoz hogy működjön, fentebb kellett a providers rész
  constructor(
    private loggingService: LoggingService,
    private accountsService: AccountsService
  ) {
    //A serviceben létrehozott eventet az account.component.ts-ben emiteltük, itt pedig feliratkoztunk rá
    //Tehát minden emit esetén (amikor megtörténik az event aktiválódása), a subscribeolók megmondhatják hogy mi történjen olyankor (itt csak egy popup ablakkal kiírtuk)
    this.accountsService.statusUpdated.subscribe((status: string) =>
      alert('New status: ' + status)
    );
  }

  onCreateAccount(accountName: string, accountStatus: string) {
    //Itt már a dependency functionját használjuk

    this.accountsService.addAccount(accountName, accountStatus);
    // this.loggingService.logStatusChange(accountStatus);
  }
}
