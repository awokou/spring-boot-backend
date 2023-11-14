import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeesComponent } from './components/employees/employees.component';
import { CreateEmployeeComponent } from './components/employees/create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './components/employees/update-employee/update-employee.component';

const routes: Routes = [
  /*******************Route employee****************/
  { path: 'employees', redirectTo: 'employees/index', pathMatch: 'full' },
  { path: 'employees/index', component: EmployeesComponent },
  { path: 'employees/create', component: CreateEmployeeComponent },
  { path: 'employees/:id/edit', component: UpdateEmployeeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
