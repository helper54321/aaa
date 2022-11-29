import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AddcontactComponent } from './addcontact/addcontact.component';
import { ContactComponent } from './contact/contact.component';
import { AuthGuard } from './Guard/auth.guard';
import { RoleGuard } from './Guard/role.guard';
import { HomeComponent } from './home/home.component';
import { StatusComponent } from './status/status.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'about', component: AboutComponent, canActivate: [AuthGuard] },

  // Itt azért roleguard, mert abban mondjuk meg, hogy csak admin érhesse el!!!!!!!!!!!!!!!!!
  { path: 'user', component: UserComponent, canActivate: [RoleGuard] },
  {
    path: 'contact',
    component: ContactComponent,
    children: [
      { path: 'add', component: AddcontactComponent },
      { path: 'edit/:id', component: AddcontactComponent },
    ],
    canActivate: [AuthGuard],
  },
  // Lazy loadingos megoldás (nekem nem fog kelleni)
  {
    path: 'access',
    loadChildren: () =>
      import('./access/access.module').then((opt) => opt.AccessModule),
  },

  // Standalone componentes megoldás (ez se kell nekem)
  {
    path: 'login',
    loadComponent: () =>
      import('./login/login.component').then((opt) => opt.LoginComponent),
  },

  { path: '**', component: StatusComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
