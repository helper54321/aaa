import { EventEmitter, Injectable } from '@angular/core';
import { Subject } from 'rxjs';

// Ez egy modernebb megoldása a providenek, de nyilván lehetne az app.module-ban is a providersnél, ahogy korábban
@Injectable({ providedIn: 'root' })
export class UserService {
  // Régi megoldáshoz kapcsolódó, a lentebbi már modernebb
  // activatedEmitter = new EventEmitter<boolean>();

  // A subject majdnem ugyanaz, mint egy observable, a fő különbség, hogy observable esetén az observable létrehozásnál csak belül használható a next, míg subject esetén kívül is, mint ahogy az user.ts-ben meg is tesszük
  // Ugyan ez most egy subject, de ugyanúgy mint observable esetén is, ahol feliratkozunk rá, utána le is kell ha már nincs rá szükség
  // Megemlítendő, hogy a fentebbi eventemitter-es megoldásnál ebben az esetben jobb a subject, azonban nem mindig, így van amikor az kell
  activatedEmitter = new Subject<boolean>();
}
