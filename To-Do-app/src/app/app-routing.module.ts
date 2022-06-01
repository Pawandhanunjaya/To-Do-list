import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditTodoDialogComponent } from './edit-todo-dialog/edit-todo-dialog.component';
import { HelpcenterComponent } from './helpcenter/helpcenter.component';
import { HomeComponent } from './home/home.component';
import { LogInComponent } from './log-in/log-in.component';
import { PrivacyComponent } from './privacy/privacy.component';
import { RegisterComponent } from './register/register.component';
import { TaskaddComponent } from './taskadd/taskadd.component';
import { TodosComponent } from './todos/todos.component';
import { AuthGuard } from './auth.guard'

const routes: Routes = [
  {path:'',component:HomeComponent},
  {
    path:'todo',
    canActivate:[AuthGuard],
    component:TodosComponent
  }, 
  {
    path:'login',
    component:LogInComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'edit',
    canActivate:[AuthGuard],
    component:EditTodoDialogComponent
  },
  {
    path:'task',
    canActivate:[AuthGuard],
    component:TaskaddComponent
  },
  {
    path:'privacy',
    component:PrivacyComponent
  },
  {
    path:'helpcenter',
    component:HelpcenterComponent
  },
  {
    path:'home',
    component:HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
