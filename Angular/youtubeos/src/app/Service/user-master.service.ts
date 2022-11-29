import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserModel } from '../Model/UserModel';

@Injectable({
  providedIn: 'root'
})
export class UserMasterService {

  apiUrl = "https://localhost:8086/api/UserMaster"

  constructor(private http: HttpClient) { }

  // Az UserModel egy interface, amit mi hoztunk létre
  GetAllUser(): Observable<UserModel[]>{
    return this.http.get<UserModel[]>(this.apiUrl)
  }

  GetUserById(userId: any){
    return this.http.get(this.apiUrl + "/" + userId)
  }

  RemoveUser(userId: any){
    return this.http.delete(this.apiUrl + "/" + userId)
  }
  UpdateUser(inputData: any){
    return this.http.post(this.apiUrl + "/ActivateUser", inputData)
  }

  GetAllRoles(){
    return this.http.get("https://localhost:8086/user/getAllRole")
  }
}
