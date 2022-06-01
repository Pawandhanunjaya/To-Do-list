import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskappService {

  constructor(private httpClient:HttpClient) { }

  url:string="http://localhost:3333/myapp/v1/task"


  token:string=''

  reqHeader:any;
  

  getAllTasks(){
    this.token=localStorage.getItem('Jwt_Token')||''
    console.log(this.token)
    this.reqHeader=new HttpHeaders().set('Authorization','Bearer '+this.token).set('access-control-allow-origin', '*');
    return this.httpClient.get<any>(this.url,{headers:this.reqHeader});
  }

 

  addTasks(data:any){
    this.token=localStorage.getItem('Jwt_Token')||''
    console.log(this.token)
    this.reqHeader=new HttpHeaders().set('Authorization','Bearer '+this.token).set('access-control-allow-origin', '*');
    return this.httpClient.post<any>(this.url,data,{headers:this.reqHeader});
  }

  deleteTask(id:number){
    this.token=localStorage.getItem('Jwt_Token')||''
    console.log(this.token)
    this.reqHeader=new HttpHeaders().set('Authorization','Bearer '+this.token).set('access-control-allow-origin', '*');
    return this.httpClient.delete<any>(this.url+"/"+id,{headers:this.reqHeader,responseType:"text" as "json"});
  }

  

  getTaskByUserId(userid:any){
    this.token=localStorage.getItem('Jwt_Token')||''
    this.reqHeader=new HttpHeaders().set('Authorization','Bearer '+this.token).set('access-control-allow-origin', '*');
    return this.httpClient.get<any>(this.url+"-by-userid/"+userid,{headers:this.reqHeader});

  }
}

