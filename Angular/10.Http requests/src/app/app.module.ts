import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AuthInterceptorService } from './auth-interceptor.service';
import { LoggingInterceptorService } from './logging-interceptor.service';

@NgModule({
  declarations: [AppComponent],
  // A httpclientmodule szükséges ahhoz, hogy http requesteket küldhessünk!!!!!!!
  imports: [BrowserModule, FormsModule, HttpClientModule],
  // Itt megmondjuk, hogy http interceptorként akarjuk használni a AuthInterceptorService classt, a multi jelentése pedig, hogy engedélyezzük-e, hogy több interceptor is legyen
  // Ha több interceptort is megadunk, akkor a sorrendjük számít, mert abban a sorrendben fognak végrehajtódni (van amikor tökmindegy milyen sorrendben hajtódik végre, akkor akárhogy megadhatjuk)
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoggingInterceptorService,
      multi: true,
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
