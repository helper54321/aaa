import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserModel } from '../Model/UserModel';
import { UserMasterService } from '../Service/user-master.service';
import * as alertify from 'alertifyjs';
import { MatDialog } from '@angular/material/dialog';
import { ModalpopupComponent } from '../modalpopup/modalpopup.component';

// Az app.module.ts-ben kellett a MaterialModule import
// Továbbá a dialoghoz is külön, ugyanott

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H' },
  { position: 2, name: 'Helium', weight: 4.0026, symbol: 'He' },
  { position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li' },
  { position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be' },
  { position: 5, name: 'Boron', weight: 10.811, symbol: 'B' },
  { position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C' },
  { position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N' },
  { position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O' },
  { position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F' },
  { position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne' },
];

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  constructor(private service: UserMasterService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.GetAllUser();
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  UserDetail: any;
  dataSource: any;

  GetAllUser() {
    this.service.GetAllUser().subscribe((item) => {
      this.UserDetail = item;
      // this.dataSource = this.UserDetail

      // Itt lentebb a <> rész opcionális?
      // this.dataSource = new MatTableDataSource(this.UserDetail);
      this.dataSource = new MatTableDataSource<UserModel>(this.UserDetail);
      this.dataSource.paginator = this.paginator;
    });
  }

  // Ezekre a html-ben hivatkozunk
  displayedColumns: string[] = [
    'userId',
    'name',
    'email',
    'isActive',
    'role',
    'Action',
  ];
  // dataSource = ELEMENT_DATA;

  FunctionUpdate(userId: any) {
    let popup = this.dialog.open(ModalpopupComponent, {
      width: '400px',
      // height: '400px',
      exitAnimationDuration: '1000ms',
      enterAnimationDuration: '1000ms',
      data: {
        userId: userId,
      },
    });

    // Akkor záródik be a popup, amikor értéket módosítottunk a modalpopupban, tehát ez olyankor fog lefutni
    popup.afterClosed().subscribe((item) => {
      this.GetAllUser();
    });
  }

  FunctionDelete(userId: any) {
    // A confirmnak 4 paramétere van: az 1. a szöveg headerje, a 2. a megjelenítendő szöveg, 3. igen click, 4. nem click

    alertify.confirm(
      'Remove user',
      'Do you want to remove this user?',
      () => {
        this.service.RemoveUser(userId).subscribe((item) => {
          // Törlés után frissítse a tartalmat (új requesttel kérje le a friss adatokat)
          this.GetAllUser();
          alertify.success('Removed successfully');
        });
      },
      function () {}
    );
  }
}
