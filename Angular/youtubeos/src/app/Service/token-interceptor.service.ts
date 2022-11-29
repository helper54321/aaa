import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// Ez egy http interceptor class, célja hogy a header authorization részébe tegye a tokent
// Ahhoz, hogy működjön az app.module.ts-ben kellett a providers részbe: 
  //  [
  //   {
  //     provide: HTTP_INTERCEPTORS,
  //     useClass: TokenInterceptorService,
  //     multi: true,
  //   },
  // ],

@Injectable({
  providedIn: 'root',
})
export class TokenInterceptorService implements HttpInterceptor {
  constructor() {}
  token = localStorage.getItem("token")

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let jwtToken = req.clone({
      setHeaders: {
        Authorization: 'bearer ' + this.token
      }
    });

    return next.handle(jwtToken);
  }
}
