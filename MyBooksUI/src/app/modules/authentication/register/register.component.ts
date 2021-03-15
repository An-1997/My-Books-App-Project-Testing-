import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'auth-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User;

  constructor(private authService: AuthenticationService, private router: Router, private snackBar:MatSnackBar) {
    this.newUser = new User();
   }

  ngOnInit() {
  }

  registerUser(){
    this.authService.registerUser(this.newUser).subscribe((data)=>{
      this.snackBar.open(`Registration successful`,'',{duration:5000});
      this.router.navigate(['/login']);
    },(err)=>{this.snackBar.open(err.error,'',{duration:5000});
    })
  }

  resetInput(){
    this.newUser=new User();
  }

}
