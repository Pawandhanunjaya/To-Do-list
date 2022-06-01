import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LogInService } from '../log-in.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  constructor(private loginService:LogInService,private router:Router) { }
  ngOnInit(): void {
  }
  loginForm=new FormGroup({
    email:new FormControl('',Validators.required),
    password:new FormControl('',Validators.required),
   
  })
  get email(){
    return this.loginForm.get('email');
  }
  get password(){
    return this.loginForm.get('password');
  }
  error:String=''
  login(){
   
      this.loginService.loginUser(this.loginForm.value).subscribe(response=>{
       
        Swal.fire('Successfully logged in')
        localStorage.setItem("User_id",response.userid);
        console.log(response.token+" "+response.username+" "+response.userid)
        localStorage.setItem("Jwt_Token",response.token);
        localStorage.setItem("User_Name",response.UserName);
        this.loginService.userLoggedIn();
        this.router.navigate(['todo'])
      },(error=>{
        this.error=error;
        console.log("Error in logging in"+error);
        Swal.fire("Please provide registered email and password")
      
      }))
  }
}