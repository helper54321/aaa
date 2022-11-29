import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/Material-Module';
import { FormsModule } from '@angular/forms';
import { UserService } from '../Service/user.service';
import { Router } from '@angular/router';

// Ahhoz hogy működjön a html-jében az ngForm itt kellett a FormsModule import!!!!!!!!
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, MaterialModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private service: UserService, private route: Router) {}

  ngOnInit(): void {
    localStorage.clear();
  }

  respData: any;

  // Ezután vissza fog adni egy tokent
  ProceedLogin(loginData: any) {
    if (loginData.valid) {
      this.service.ProceedLogin(loginData.value).subscribe((item) => {
        this.respData = item;
        if (this.respData != null) {
          localStorage.setItem('token', this.respData.jwtToken);
          this.route.navigate(["home"])
        } else {
          alert('Login failed');
        }
      });
    }
  }
  
  RedirectRegister(){
    this.route.navigate(["access/register"])
  }
}
