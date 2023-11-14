import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee.model';
import { EmployeeService } from '../../service/employee.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees!: Employee[];

  constructor(
    private _employeeService: EmployeeService,
    private router: Router) {}

  ngOnInit() {
    this._loadAllEmployees();
  }

  _loadAllEmployees() {
    this._employeeService.getListOfAllEmployees()
    .subscribe(
     data => {
      this.employees = data;
      console.log(JSON.stringify(data));
    })
  }

  onDelete(id: number) {
    this._employeeService.deleteEmployee(id).subscribe(data => {
      console.log(data);
      this._loadAllEmployees();
    },error => console.log(error));
  }
}
