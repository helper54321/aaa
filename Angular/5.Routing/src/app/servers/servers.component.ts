import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServersService } from './servers.service';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css'],
})
export class ServersComponent implements OnInit {
  public servers: { id: number; name: string; status: string }[] = [];

  constructor(private serversService: ServersService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.servers = this.serversService.getServers();
  }

  onReload() {
    //Home.ts-ben is van kapcsolódó
    // Ez szintén a reactes history.push megfelelője
    // A html-ben a routerLink esetén ha így volna megadva, akkor a /servers/servers-re vinne
    //Itt viszont nem tudja (alapból) hogy melyik pathen vagyunk, így a /servers-re vinne, mivel mindig úgy értelmezi, hogy a /-en vagyunk (főoldal)
    //Ha mégis valamihez hozzá akarjuk fűzni, akkor meg kell adni a relativeTo részt, a route változóval pedig kinyertük, hogy épp hol vagyunk, így át tudjuk adni
    //Kikommenteltem, mert a jelenlegi esetben hibát adna, mert nincs ilyen routeunk
    
    // this.router.navigate(['servers'], {relativeTo: this.route});
  }
}
