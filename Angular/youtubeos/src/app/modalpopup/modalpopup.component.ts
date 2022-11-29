import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserMasterService } from '../Service/user-master.service';
import * as alertify from "alertifyjs"

@Component({
  selector: 'app-modalpopup',
  templateUrl: './modalpopup.component.html',
  styleUrls: ['./modalpopup.component.css'],
})
export class ModalpopupComponent implements OnInit {
  // Itt az @Inject résszel kapja meg kintről az id-t, kitudja hogyan (de ha jól sejtem, ez is materialUI-al kapcsolatos)
  constructor(
    private service: UserMasterService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private ref:MatDialogRef<ModalpopupComponent>
  ) {}

  ngOnInit(): void {
    this.GetAllRole();
    this.GetExistingData(this.data.userId);
  }

  roleData: any;
  editData: any;
  saveData: any;

  // Itt lentebb, pl a role 1. paramétere is, egy default érték (de mivel az oninit-ben, úgyis értéket kap, így itt csak azért kell, mert muszáj valamit megadni)
  updateForm = new FormGroup({
    userId: new FormControl({ value: '', disabled: true }),
    role: new FormControl('', Validators.required),
    isActive: new FormControl(true),
  });

  SaveUser() {
    if (this.updateForm.valid) {
      // Itt azért a lentebbi kell, mert használtunk fentebb egy disabled value-t, így ilyenkor a rawValue kell
      // this.service.UpdateUser(this.updateForm.value)
      this.service
        .UpdateUser(this.updateForm.getRawValue())
        .subscribe((item) => {
          this.saveData = item;
          // Az ő backendje siker esetén ezt adja vissza
          if(this.saveData.result == "pass"){
            alertify.success("Updated successfully")
            // Ez is materialos (de nekem úgyse kéne most)
            this.ref.close();
          }else{
            alertify.error("Failed, try again")
          }
        });
    }
  }

  GetAllRole() {
    this.service.GetAllRoles().subscribe((item) => {
      this.roleData = item;
    });
  }

  GetExistingData(userId: any) {
    this.service.GetUserById(userId).subscribe((item) => {
      this.editData = item;
      if (this.editData != null) {
        this.updateForm.setValue({
          userId: this.editData.userId,
          role: this.editData.role,
          isActive: this.editData.isActive,
        });
      }
    });
  }
}
