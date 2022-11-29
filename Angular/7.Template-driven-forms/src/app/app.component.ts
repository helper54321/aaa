import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  // A html-ben lévő formnál lévő #f-hez van hozzákapcsolva, azért van itt f megadva (kb useref megoldás)
  // Ebben az esetben nem paraméterként adtuk át a submitnél (azt ki is kommenteltem)
  // Ennek előnye, hogy így submit előtt is hozzáférhetünk az adatokhoz, míg a másik esetnél nem
  @ViewChild('f') signupForm: NgForm;

  defaultQuestion = 'pet';
  answer = '';
  genders = ['male', 'female'];
  user = {
    username: '',
    email: '',
    secretQuestion: '',
    answer: '',
    gender: '',
  };

  submitted = false

  suggestUserName() {
    const suggestedName = 'Superuser';

    // Itt a formnak állítunk be manuálisan egy értéket (vagyis az inputoknak), de ehhez a form egészének értékét kell, tehát minden value értéket kell kapjon
    // Felhasználása, hogy amikor a formban a suggest buttonra kattintunk akkor történjen meg ez az egész
    // Hátránya, hogy ha már volt valami beírva, azokat felülírja ezzel

    // this.signupForm.setValue({
    //   userData: {
    //     username: suggestedName,
    //     email: ""
    //   },
    //   secret: "pet",
    //   questionAnswer: "",
    //   gender: "male"
    // })

    // Ez ugyanaz, mint a fenti kikommentelt, azzal a lényeges különbséggel, hogy itt már csak ténylegesen azt adjuk meg amit felül akarunk írni (tehát összefésüli, és csak az kap új értéket amit megadunk)
    this.signupForm.form.patchValue({
      userData: {
        username: suggestedName,
      },
    });
  }

  // Ameddig a htmlben a formnál simán #f volt, egyenlőség nélkül, addig ez volt érvényes
  // onSubmit(form:HTMLFormElement){

  // Az ngFormos megoldáshoz
  // onSubmit(form:NgForm){
  //   console.log(form)
  // }

  // A viewchild-os megoldáshoz
  onSubmit() {
    console.log(this.signupForm);
    
    this.submitted = true

    this.user.username = this.signupForm.value.userData.username
    this.user.email = this.signupForm.value.userData.email
    this.user.secretQuestion = this.signupForm.value.secret
    this.user.answer = this.signupForm.value.questionAnswer
    this.user.gender = this.signupForm.value.gender

    // Miután már úgyis kivettük az értékeket, így visszaállítjuk defaultra (nullázzuk az értékeit)
    // Ez nem csak az értékeit defaultolja, hanem a státuszait is, pl touched, valid, stb.
    // Ehelyett használhatnánk a fentebbi setvaluet is akár, ahol általunk akart adatokkal töltenénk fel, ha szükséges
    this.signupForm.reset()
  }
}
