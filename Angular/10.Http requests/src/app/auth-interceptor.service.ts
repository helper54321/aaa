import {
  HttpEvent,
  HttpEventType,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { map, Observable, tap } from 'rxjs';

export class AuthInterceptorService implements HttpInterceptor {
  // Egy interceptor minden egyes http request előtt automatikusan lefut (tehát olyan dolgokat amit mindenhez meg akarunk adni, mint pl authentication token hozzáadása, itt megtehető)
  // Ahhoz hogy működjön, az app.module-ban a providersnél import kell!!!!!!!!!!
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    console.log('Request is on its way');
    console.log(req.url);

    const modifiedRequest = req.clone({
      headers: req.headers.append('Auth', 'xyz'),
    });

    // Mivel itt hozzáférésünk van a requesthez, így feltételeket is szabhatunk hogy mikor vagy milyen formában akarjuk a kód lefutását (pl ha bizonyos url esetén nem akarjuk, akkor nézhetjük, hogy req.url === "akármi")

    // Fentebb elvégezzük azt amit akarunk (pl token headerbe tétele), itt pedig megmondjuk, hogy folytassa a request normál végrehajtásával
    // Itt a pipe rész már a request elküldése után hajtódik végre (tehát kb mintha subscribeolnánk)
    // A posts.service-ben be lett mutatva, hogy többféleképpen is megkaphatjuk a requestet, köztük az egyik volt az event. Itt viszont mindig eventet kapunk, függetlenül attól, hogy a requestnél mi van megadva
    return next.handle(modifiedRequest).pipe(
      tap((event) => {
        if (event.type === HttpEventType.Response) {
          console.log('Response arrived');
          console.log(event.body)
        }
      })
    );
  }
}
