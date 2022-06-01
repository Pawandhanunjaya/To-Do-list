import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient:HttpClient) { }

  url:string="http://localhost:3333/myapp/auth/register"

  registerUser(data:any){
    return this.httpClient.post<any>(this.url,data);
  }

}
