import { HttpClient, HttpEventType, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from './post.model';
import { map, catchError, tap } from 'rxjs/operators';
import { Subject, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PostsService {
  // Azokhoz az esetekhez, ahol a serviceben subscribeolunk
  error = new Subject<string>();

  constructor(private http: HttpClient) {}

  createAndStorePost(title: string, content: string) {
    const postData: Post = { title: title, content: content };
    this.http
      .post<{ name: string }>(
        'https://angular-http-86ae3-default-rtdb.europe-west1.firebasedatabase.app/posts.json',
        postData,
        // Itt opcionálisan megmondhatjuk, hogy majd a subscribe esetén ahol visszajön a response, mit akarunk belőle megkapni
        // Observenél a body esetén a response bodyját, míg response esetén a teljes response (header, status code, stb. is)
        // A responses esetén már response.body-val érnénk el a body részét
        // Lentebb a deletes functionnál events is van, annak jelentése hogy milyen állapotban van a request (kb mint ajaxnél) (tehát pl 0: sent, 4: response, stb)
        // Az eventes elég ritkán használatos
        {
          // observe: "body"
          observe: 'response',

          // Ez a rész nagyjából magától értetődő, milyen formában adja vissza a responset
          // Azonban az esetek 99%-ában json-t akarunk (a default is az), így meg se kell adni
          responseType: "json"
          // responseType: "text"
          // responseType: "blob"
        }
      )
      .subscribe(
        (responseData) => {
          console.log(responseData);
        },
        (error) => {
          this.error.next(error.message);
        }
      );
  }

  //   Ennél return-oljuk és nem itt subscribeolunk, mert a hívó oldalon a loading miatt ott iratkozunk fel, tehát ott tudni akarjuk az aktuális állapotot, míg pl a fentebbi post methodnál nem
  fetchPosts() {
    // Azért így, mert ez immutable (tehát itt az appendet mindig a már előző állapothoz adja, tehát nem felülírjuk, hanem a 2. append már az 1. mellé kerül be)
    let searchParams = new HttpParams();
    searchParams = searchParams.append('print', 'pretty');
    searchParams = searchParams.append('custom', 'key');
    return (
      this.http
        // Itt a get<> között megadhatjuk (ha akarjuk, nem kötelező, de érdemes lehet) a visszakapott (visszavárt) adat formátumát
        .get<{ [key: string]: Post }>(
          'https://angular-http-86ae3-default-rtdb.europe-west1.firebasedatabase.app/posts.json',
          {
            // Ez egy opcionális rész ha szükséges, itt adhatunk headert a http requesteknek (itt csak példa, nem sok értelme van)
            headers: new HttpHeaders({
              'Custom-header': 'Hello',
            }),
            // Itt pedig queryParamétereket adhatunk meg ha szükséges. A print és a hozzá tartozó pretty (ami a megjelenítést befolyásolja, tehát csak vizuális) firebase specifikus, saját backendünknél az volna amit mi lehetővé teszünk
            // Tehát a params rész a url végéhez teszi a paramétert, pl: localhost:8086/valami?print=pretty
            // Fentebb a url-nél is ugyanúgy megadhattuk volna, így talán annyi az előnye hogy átláthatóbb az url

            // params: new HttpParams().set('print', 'pretty'),
            // Így is lehet, fentebbre kivezettük (főleg akkor érdemes ha több paramétert akarunk, mint itt is, de itt csak a példa miatt)
            params: searchParams,
          }
        )
        // Itt átalakítjuk a formáját, mire a subscribe részben már megkapja, már módosítva fogja
        .pipe(
          map((responseData) => {
            const postsArray: Post[] = [];

            for (const key in responseData) {
              if (responseData.hasOwnProperty(key)) {
                postsArray.push({ ...responseData[key], id: key });
              }
            }

            return postsArray;
          }),
          // Így is vissza lehetne adni a hibát ha akarjuk
          catchError((errorRes) => {
            return throwError(errorRes);
          })
        )
    );
  }

  deletePosts() {
    return this.http
      .delete(
        'https://angular-http-86ae3-default-rtdb.europe-west1.firebasedatabase.app/posts.json',
        {
          observe: 'events',
        }
      )
      // A tap jelentése, hogy csinálhatunk valamit az adattal (amit akarunk), de az eredeti adatot nem módosítja
      // Az eventes részt fentebb magyarázom
      .pipe(
        tap((event) => {
          console.log(event);

          if(event.type === HttpEventType.Response){
            console.log(event.body)
          }
        })
      );
  }
}
