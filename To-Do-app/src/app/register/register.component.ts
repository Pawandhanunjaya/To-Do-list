import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../register.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private registerService:RegisterService,private router:Router) { }

  ngOnInit(): void {
  }

  registerForm=new FormGroup({
    username:new FormControl('',[Validators.required, Validators.minLength(4)]),
    password:new FormControl('',[Validators.required, Validators.minLength(4)]),
    email:new FormControl('',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
  })
  get username(){
    return this.registerForm.get('username');
  }
  get password(){
    return this.registerForm.get('password');
  }
  get email(){
    return this.registerForm.get('email');
  }
   userName:any

  register(){
    this.userName=localStorage.getItem("username");
    this.registerService.registerUser(this.registerForm.value).subscribe(response=>{
      // alert("Registration successful\nYour user id is "+response.userid);
      Swal.fire('Registration successful\nYour user id is '+response.userid)
      this.router.navigateByUrl('/login');
    })
  }


}
