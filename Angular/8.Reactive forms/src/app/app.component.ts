import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  genders = ['male', 'female'];

  signupForm: FormGroup;
  forbiddenUsernames = ['Chris', 'Anna'];

  ngOnInit(): void {
    // Lehet nested formgroup, mint itt is
    // Lentebb az usernamenél egy custom validator van (egyszerű function, amit mi hoztunk létre), emailnél pedig custom async
    this.signupForm = new FormGroup({
      userData: new FormGroup({
        username: new FormControl(null, [
          Validators.required,
          this.forbiddenNames.bind(this),
        ]),
        // Fentebb a custom validator simán mehetett a [] közé (2. paraméter), míg itt a 3. paraméter (itt is lehetne [] közé, ha több lenne). Azért kell a 3. paraméterhez, mert a 2. sync, míg a 3. async validatoroknak van fenntartva
        // Ennél csak azért nem bindoljuk a this-t, mert abban a functionban úgyse hivatkozunk rá, amúgy kellene (opcionálisan itt is lehetne, de felesleges)
        email: new FormControl(
          null,
          [Validators.required, Validators.email],
          this.forbiddenEmails
        ),
      }),
      gender: new FormControl('male'),
      // Ehhez click listener használatával dinamikusan adunk hozzá
      hobbies: new FormArray([]),
    });

    // A lentebb 2 esetre subscribeolunk, érték és status változása (lehetne az egyes controleokra is, nem kötelező az egész formra)
    // Ezt pedig használhatjuk arra, hogy attól függően történjen valami (pl async validator esetén kiírja a pendinget, amíg folyamatban van)

    // this.signupForm.valueChanges.subscribe(value => {
    //   console.log(value)
    // })

    this.signupForm.statusChanges.subscribe((status) => {
      console.log(status);
    });

    // Mint ahogy a template driven esetben, itt is beállítható a form értéke (set esetén teljes felülírás, patch esetén részleges adatmódosítás)
    this.signupForm.setValue({
      userData: {
        username: 'Max',
        email: 'max@test.com',
      },
      gender: 'male',
      hobbies: [],
    });

    this.signupForm.patchValue({
      userData: {
        username: 'Anna',
      },
    });
  }

  getControls() {
    return (<FormArray>this.signupForm.get('hobbies')).controls;
  }

  onAddHobby() {
    const control = new FormControl(null, Validators.required);
    (<FormArray>this.signupForm.get('hobbies')).push(control);
  }

  onSubmit() {
    console.log(this.signupForm);

    // Mindent defaultra állít. Ha pl a radio buttont nem akarnánk átadhatnánk egy objectet paraméterként
    this.signupForm.reset();
  }

  // Ezt egy custom validatorhoz használjuk (legtöbbször nem szükséges, általában elég a beépített)
  // Kicsit komplikáltnak néz ki a paraméter rész, de lényegében ilyesmi formát kell visszaadnia: {nameIsForbidden: true}
  // Sikeres esetben viszont kötelezően semmit, vagy nullt kell visszaadni
  // Itt most hardcodeolt a forbidden neveket tartalmazó array, de lehetne pl http request eredménye is, és akkor azzal hasonlíthatnánk (lentebb van is hasonlóra példa)
  forbiddenNames(control: FormControl): { [s: string]: boolean } {
    if (this.forbiddenUsernames.indexOf(control.value) !== -1) {
      return { nameIsForbidden: true };
    }

    return null;
  }

  // Ez szintén egy custom validatorként szolgál, de a fentivel ellentétben async task esetére (most csak szimuláljuk)
  forbiddenEmails(control: FormControl): Promise<any> | Observable<any> {
    const promise = new Promise<any>((resolve, reject) => {
      setTimeout(() => {
        if (control.value === 'test@test.com') {
          resolve({ emailIsForbidden: true });
        } else {
          resolve(null);
        }
      }, 1500);
    });

    return promise;
  }
}
