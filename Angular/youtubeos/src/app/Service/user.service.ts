import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

// Ahhoz hogy a login és registerhez használhassuk az app.module.ts-ben kellett a HttpClientModule import!!!!!!!

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  ProceedLogin(inputData: any) {
    return this.http.post('https://localhost:8086/user/login', inputData);
  }

  // Az authguardhoz felhasználjuk, hogy levédje-e a path-et
  IsLoggedIn() {
    return localStorage.getItem('token') != null;
  }

  GetToken() {
    return localStorage.getItem('token') != null
      ? localStorage.getItem('token')
      : '';
  }

  Registration(inputData: any) {
    return this.http.post('https://localhost:8086/user/register', inputData);
  }

  // Itt történik meg a tokenből a role kinyerése!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  // Használni az app.component.ts-ben és annak html-jében (mivel most ott vannak a linkjeink) fogjuk
  GetRole() {
    let token = this.GetToken();
    if (token != null) {
      let extractData = JSON.parse(
        Buffer.from(token?.split('.')[1], 'base64').toString()
      );
      return extractData.role;
    }

    return '';
  }
}
