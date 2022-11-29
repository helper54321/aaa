import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/auth-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit() {}

  onLoadServer(id: number) {
    //Servers.ts-ben is van kapcsolódó
    // A reactes history.push megfelelője (html-ben clicklistenerhez rendeltük)
    //Tehát előtte lehet kódlefutás, és annak a végén irányítjuk át ha akarjuk

    // Ez a /servers/1/edit?allowEdit=1#loading címre irányít
    // Ezen értékek kinyeréséhez a kód az edit-server.component.ts-ben van
    this.router.navigate(['/servers', id, 'edit'], {
      queryParams: { allowEdit: '1' },
      fragment: 'loading',
    });
  }

  onLogin() {
    this.authService.login();
  }

  onLogout() {
    this.authService.logout();
  }
}
