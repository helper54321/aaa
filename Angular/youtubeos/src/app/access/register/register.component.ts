import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Service/user.service';

// A styles.css-be is kellettek importok, továbbá kellett létrehozni egy alertifytype.d.ts fájlt
// Atsconfig.json-ben pedig a 20. sor körül kellett a "typeRoots": ["node_modules/@types","src/alertifytype.d.ts"],
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private router: Router, private service: UserService) {}

  respData: any;

  ngOnInit(): void {}

  RedirectLogin() {
    this.router.navigate(['login']);
  }

  reactiveForm = new FormGroup({
    userId: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    // Itt azért compose, mert így lehetséges 1-nél többet megadni
    email: new FormControl(
      '',
      Validators.compose([Validators.required, Validators.email])
    ),
  });

  SaveUser() {
    console.log(this.reactiveForm.valid);
    console.log(this.reactiveForm.value);
    // Itt az egyes változók értéke is kivehető
    console.log(this.reactiveForm.value.name);

    if (this.reactiveForm.valid) {
      this.service.Registration(this.reactiveForm.value).subscribe((item) => {
        this.respData = item;
        // Az ő verziójávan sikeres regisztráció esetén a visszaadott értékben van egy result változó, azt vizsgáljuk itt
        if (this.respData.result == 'pass') {
          alertify.success("Successful registration")
          this.RedirectLogin();
        }
        alertify.error('Failed, try again');
      });
    }
  }
}
