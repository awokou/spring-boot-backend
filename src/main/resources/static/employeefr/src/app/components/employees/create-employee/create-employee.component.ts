import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../service/employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employeeForm!: FormGroup;
  errorMessage!: string;

  constructor(private _employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit(): void {
    this.employeeForm = new FormGroup({
      lastName: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required)
    });
  }

  onCreateSubmit() {
    if (this.employeeForm.valid) {
      this._employeeService.createNewEmployee(this.employeeForm.value)
        .subscribe({
          next: (response: any) => {
            console.log(response);
            this.router.navigate(['/employees']);
          },
          error: (error) => {
           console.log('error',error);
            this.errorMessage = error.error.message;
          },
        });
    } else {
      this.employeeForm.markAllAsTouched();
    }
  }
}
