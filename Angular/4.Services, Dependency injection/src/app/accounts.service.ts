import { EventEmitter, Injectable } from '@angular/core';
import { LoggingService } from './logging.service';

//Mivel ebbe a servicebe még egy servicet akarunk (nested service), a loggingServicet ezért meg kellett adni az @Injectable() részt
//Ugyanis csak olyan helyre tudunk injektálni ami rendelkezik decoratorral (pl @Component, @Directive)
//Service esetén alapból nincsen, és (jelenleg) még nem is kötelező hogy legyen, csak olyan esetekben ahová nestedelni akarunk (mint itt is)
@Injectable()
export class AccountsService {
  accounts = [
    {
      name: 'Master Account',
      status: 'active',
    },
    {
      name: 'Testaccount',
      status: 'inactive',
    },
    {
      name: 'Hidden Account',
      status: 'unknown',
    },
  ];

  //Eventeket is használhatunk a serviceben (hozzákapcsolódó kód a new-account, és account ts-eiben)
  statusUpdated = new EventEmitter<string>();

  constructor(private loggingService: LoggingService) {}

  addAccount(name: string, status: string) {
    this.accounts.push({ name: name, status: status });
    this.loggingService.logStatusChange(status);
  }

  updateStatus(id: number, status: string) {
    this.accounts[id].status = status;
    this.loggingService.logStatusChange(status);
  }
}
