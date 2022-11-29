import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccessRoutingModule } from './access-routing.module';
import { RegisterComponent } from './register/register.component';
import { MaterialModule } from 'src/Material-Module';
import { ReactiveFormsModule } from '@angular/forms';


// Ahhoz, hogy a reactive form működjön, lentebb kellett a ReactiveFormsModule import!!!!!!!

@NgModule({
  declarations: [
    RegisterComponent
  ],
  imports: [
    CommonModule,
    AccessRoutingModule,
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class AccessModule { }
