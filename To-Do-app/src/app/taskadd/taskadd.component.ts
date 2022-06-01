import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TaskappService } from '../todos/taskapp.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-taskadd',
  templateUrl: './taskadd.component.html',
  styleUrls: ['./taskadd.component.css']
})
export class TaskaddComponent implements OnInit {
  constructor(private taskService:TaskappService,private router:Router,private snackBar:MatSnackBar) { }
 
  image!: any;
  url:any="";
  taskList:any;


  todayDate:any
  
  currentDate(){
    let currentDate=new Date().getDate()<10?"0"+new Date().getDate():new Date().getDate()
    let currentMonth=(new Date().getMonth()+1)<10?"0"+(new Date().getMonth()+1):new Date().getMonth()+1
    let currentYear=new Date().getFullYear()
    this.todayDate=currentYear+"-"+currentMonth+"-"+currentDate
    localStorage.setItem("CurrentDate",this.todayDate)
    console.log(currentYear+"  "+currentMonth+"  "+currentDate+" "+localStorage.getItem("CurrentDate"))
  }

  onuploadimage(event:any){
    
    console.log(event)
    
    if(!event.target.files[0] || event.target.files[0].length==0){
      return;
    }
    let mimeType=event.target.files[0].type;
    if(mimeType.match(/image\/*/)==null){
      return;
    }
    let reader=new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event)=>{
      this.url=reader.result;
      console.log(this.url)
      this.image=this.url
    }
    
  }

  initialValues:any
  username:String;
  ngOnInit(): void {
    this.currentDate();
   // localStorage.setItem("mindate",this.todayDate);
    this.initialValues=this.TodoForm.value;
    
    this.username=localStorage.getItem("User_Name"); 
     
  }
​

  TodoForm=new FormGroup({
    taskid:new FormControl("",Validators.required),
    title:new FormControl(""),
    date:new FormControl("",Validators.required),
    priority:new FormControl("",Validators.required),
   
  });
  get taskid(){
    return this.TodoForm.get('taskid');
  }
  get usertitle(){
    return this.TodoForm.get('title');
  }
  get date(){
    return this.TodoForm.get('date');
  }
  get priority(){
    return this.TodoForm.get('priority');
  }

  userId:any

  addTask():void{
    this.userId=localStorage.getItem("User_id")
    console.log(this.TodoForm.value+","+this.image+","+localStorage.getItem("User_id"))
        this.taskService.addTasks(
          {
            taskid:this.TodoForm.value.taskid,
            title:this.TodoForm.value.title,
            date:this.TodoForm.value.date,
            priority:this.TodoForm.value.priority,
            image:this.image,
            userid:this.userId,
            
          }).subscribe(response=>{
         
          Swal.fire('Task record added')
          this.router.navigateByUrl('todo');
         
        });
        this.image=''  
  }

  
}
