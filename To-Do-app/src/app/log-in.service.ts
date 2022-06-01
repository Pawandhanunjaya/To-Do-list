import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogInService {

  constructor(private httpClient:HttpClient) { }

  url:string="http://localhost:3333/myapp/auth/login"

  loginUser(data:any):Observable<any>{
    return this.httpClient.post<any>(this.url,data);
  }

 

  isLoggedIn:boolean=false

  userLoggedIn(){
    this.isLoggedIn=true;
    console.log(this.isLoggedIn)
  }


}
