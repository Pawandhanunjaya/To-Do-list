import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TodosComponent } from './todos/todos.component';
import { MatListModule } from '@angular/material/list';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { HttpClientModule } from '@angular/common/http';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { LogInComponent } from './log-in/log-in.component';
import { RegisterComponent } from './register/register.component';
import { EditTodoDialogComponent } from './edit-todo-dialog/edit-todo-dialog.component';
import { HomeComponent } from './home/home.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatBadgeModule } from '@angular/material/badge';
import { FooterComponent } from './footer/footer.component';
import {MatSidenavModule} from '@angular/material/sidenav';

import { MatDialogModule } from '@angular/material/dialog';
import { TaskaddComponent } from './taskadd/taskadd.component';
import { PrivacyComponent } from './privacy/privacy.component';
import { HelpcenterComponent } from './helpcenter/helpcenter.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { LogInService } from './log-in.service'


@NgModule({
  declarations: [
    AppComponent,
    TodosComponent,
   
     RegisterComponent,
    LogInComponent,
    EditTodoDialogComponent,
    HomeComponent,
    FooterComponent,
    PrivacyComponent,
    HelpcenterComponent,
    TaskaddComponent,
    
    

    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
     LayoutModule, 
      MatListModule,
      MatSnackBarModule,
      MatBadgeModule,
      MatSidenavModule,
      MatDialogModule,
      NgxPaginationModule
    
     
  
  ],
  providers: [LogInService],
  bootstrap: [AppComponent],
  schemas:[NO_ERRORS_SCHEMA]
})
export class AppModule { }
