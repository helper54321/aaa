import { Component, Input } from '@angular/core';
import { AccountsService } from '../accounts.service';
import { LoggingService } from '../logging.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  // providers: [LoggingService],
})
export class AccountComponent {
  @Input() account: { name: string; status: string };
  @Input() id: number;

  //Ez itt dependency injection. A new-account.component.ts-ben részletesen magyarázom.
  constructor(
    private loggingService: LoggingService,
    private accountsService: AccountsService
  ) {}

  onSetTo(status: string) {
    this.accountsService.updateStatus(this.id, status);
    // this.loggingService.logStatusChange(status);

    //A serviceben lévő eventet aktiváljuk (itt még nem csinál vele semmit, csak aktiválja)
    //Akik pedig feliratkoznak rá, azok megmondhatják hogy ezt követően mi történjen (a new-account.component.ts-ben meg is tesszük)
    this.accountsService.statusUpdated.emit(status);
  }
}
