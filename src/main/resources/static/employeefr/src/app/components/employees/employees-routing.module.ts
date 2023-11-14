import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { EmployeesComponent } from './employees.component';

const routes: Routes = [
    { path: 'employees', redirectTo: 'employees/index', pathMatch: 'full' },
    { path: 'employees/index', component: EmployeesComponent },
    { path: 'employees/create', component: CreateEmployeeComponent },
    { path: 'employees/:id/edit', component: UpdateEmployeeComponent }
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class EmployeesRoutingModule { }
