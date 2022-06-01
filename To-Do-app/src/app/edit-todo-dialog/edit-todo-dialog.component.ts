import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TaskappService } from '../todos/taskapp.service';
​import Swal from 'sweetalert2';
@Component({
  selector: 'app-edit-todo-dialog',
  templateUrl: './edit-todo-dialog.component.html',
  styleUrls: ['./edit-todo-dialog.component.css']
})
export class EditTodoDialogComponent implements OnInit {

  constructor(private taskService:TaskappService,private router:Router) { }
 
  image!: any;
  url:any="";
  taskList:any;

  minDate:any
  
  currentDate(){
    let currentDate=new Date().getDate()<10?"0"+new Date().getDate():new Date().getDate()
    let currentMonth=(new Date().getMonth()+1)<10?"0"+(new Date().getMonth()+1):new Date().getMonth()+1
    let currentYear=new Date().getFullYear()
    this.minDate=currentYear+"-"+currentMonth+"-"+currentDate
    localStorage.setItem("newCurrentDate",this.minDate)
    console.log(currentYear+"  "+currentMonth+"  "+currentDate+" "+localStorage.getItem("newCurrentDate"))
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
    reader.onload = ()=>{
      this.url=reader.result;
      console.log(this.url)
      this.image=this.url
    }
    
  }

  taskTitle:string;
  ngOnInit(): void {
    this.assignValue();
    this.currentDate();
   
   
  
   }

  assignValue(){
    this.TodoForm.patchValue({
      title:localStorage.getItem("Tasktitle"),
      date:localStorage.getItem("TaskDate"),
      priority:localStorage.getItem("TaskPriority")
    })
  }
​
  TodoForm=new FormGroup({
    taskid:new FormControl(),
    title:new FormControl(),
    date:new FormControl(),
    priority:new FormControl(),
   
  });
  get taskid(){
    return this.TodoForm.get('taskid');
  }
  get usertitle(){
    return this.TodoForm.get('title');
  }
  get datetimelocal(){
    return this.TodoForm.get('date');
  }
  get priority(){
    return this.TodoForm.get('priority');
  }
  ​
  userId:any

  updateTask():void{
    this.userId=localStorage.getItem("User_id")
    console.log(this.TodoForm.value+","+this.image+","+localStorage.getItem("User_id"))
      
        this.taskService.addTasks(
          {
            taskid:localStorage.getItem("TaskId"),
            title:this.TodoForm.value.title,
            date:this.TodoForm.value.date,
            priority:this.TodoForm.value.priority,
            image:this.image,
            userid:this.userId
          }).subscribe(response=>{
          // alert('Task record added');
          Swal.fire('Task record added')
          localStorage.setItem("TaskId","");
          this.router.navigateByUrl('todo')
        });
      
        this.image=''  
  }

  
  

}
