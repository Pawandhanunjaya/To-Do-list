import { style } from '@angular/animations';
import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TaskappService } from './taskapp.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {NgxPaginationModule} from 'ngx-pagination';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {
  display = "none";
  
openModal() {
    this.display = "block";
    
    
  }
  onCloseHandled() {
    this.display = "none";
  }

  constructor(private taskService:TaskappService,private router:Router,private snackBar:MatSnackBar) { }
 
  image!: any;
  url:any="";
  taskList:any;
 
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
    this.initialValues=this.TodoForm.value;
    this.getTasks();
    this.username=localStorage.getItem("User_Name"); 
    this.searchBy(); 
  }


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
          alert('Task record added');
          location.reload();
          this.getTasks();
        });
        this.image=''  
  }

  getTasks(){
    this.userId=localStorage.getItem("User_id")
    this.taskService.getTaskByUserId(this.userId)
    .subscribe(response=>{
      this.taskList=response;
      this.taskList.sort(function(a:any,b:any){
       
        if(a.priority=='Low')
        {
        return 0;
        } 
        
        else if(a.priority>b.priority)
        {
          return 1;
        }
        return -1;
        })
      let currentDate=new Date(); 
      this.incomplete=this.taskList.filter((a:any)=>{
        let a_date=new Date(a.date);
        return a_date<=currentDate;
      })
      this.noOfIncomplete=this.incomplete.length;
    });
  }

  deleteTask(taskid:any):void{
    this.taskService.deleteTask(taskid)
    .subscribe(response=>{
      Swal.fire("Task Deleted")
      
      this.getTasks();
    }); 
  }

  toEdit(task:any){
    localStorage.setItem("TaskId",task.taskid);
    localStorage.setItem("Tasktitle",task.title);
    localStorage.setItem("TaskDate",task.date);
    localStorage.setItem("TaskPriority",task.priority);
    localStorage.setItem("TaskImage",task.image);
    this.router.navigateByUrl('/edit');
  }
  toTask(){
    
    this.router.navigateByUrl('/task');
  }

  openSnackBar(message:string,action:any,taskid:string){
    let snackBarRef=this.snackBar.open(message,action);

    snackBarRef.afterDismissed().subscribe(()=>{
      console.log('After completion'+taskid);
      this.deleteTask(taskid);
      this.getTasks();
    })
  }

 
  isBadgeHidden:boolean=true
  noOfIncomplete:number=0;
  incomplete:any


  search:string='All'
  searchDate:any
  searchTitle:string=''
  searchPriority:string
  searchList:any

  searchBy(){
    this.searchList=this.taskList
    if(this.search==='Date' && this.searchDate!==null){
      let date=new Date(this.searchDate);
      console.log(new Date(this.searchDate)+"  sd  "+date)
      return this.searchList=this.taskList.filter((a:any)=>{ return a.date==this.searchDate})
    }
    else if(this.search==='Title' && this.searchTitle.length>=1){
      return this.searchList=this.taskList.filter((a:any)=>{return a.title.toLowerCase().includes(this.searchTitle)})
    }
    else if(this.search==='Priority'){
      return this.searchList=this.taskList?.filter((a:any)=>{return a.priority==this.searchPriority})
    }
  }




  p:number=1
}


