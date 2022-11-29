import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { StatusComponent } from './status/status.component';
import { LoginComponent } from './login/login.component';
import { AddcontactComponent } from './addcontact/addcontact.component';
import { AccessRoutingModule } from './access/access-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserComponent } from './user/user.component';
import { MaterialModule } from 'src/Material-Module';
import { TokenInterceptorService } from './Service/token-interceptor.service';
import { ModalpopupComponent } from './modalpopup/modalpopup.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    ContactComponent,
    StatusComponent,
    AddcontactComponent,
    UserComponent,
    ModalpopupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    // Szükséges ahhoz, hogy a loginhoz használhassuk a Serviceünket!!!!!!!!
    HttpClientModule,
    ReactiveFormsModule

    //Standalone component ide kerülhet (engem nem érint, úgyse kell ilyen)
    // LoginComponent,

    //Létrehoztunk egy külön module-t, ahol a regisztrációhoz kapcsolatos pathek vannak (tehát annak a routing fájlába, ezért ide import kell)
    //Később a lazy loading miatt kikommenteltük

    // AccessRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
