import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Employee } from '../../../model/employee.model';
import { EmployeeService } from '../../../service/employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

    id!: number;
    employee!: Employee;
    employeeForm!: FormGroup;
    errorMessage!: string;

    constructor(private _employeeService: EmployeeService,
        private router: Router,
        private route: ActivatedRoute)
        { }

    ngOnInit(): void {
        this.id = this.route.snapshot.params['id'];
        this._employeeService.getEmployeeById(this.id).subscribe((data: Employee) => {
          this.employee = data;
          this.updateForm(this.employee);
        });

        this.employeeForm = new FormGroup({
            lastName: new FormControl('', Validators.required),
            firstName: new FormControl('', Validators.required),
            email: new FormControl('', Validators.required)
        });
    }

    updateForm(employee: any) {
        this.employeeForm.patchValue({
          lastName: employee?.lastName,
          firstName: employee?.firstName,
          email: employee?.email
        });
    }

    onUpdateSubmit() {
        if (this.employeeForm.valid) {
          this._employeeService.updateEmployee(this.id,this.employeeForm.value)
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
