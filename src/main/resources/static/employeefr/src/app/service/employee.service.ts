import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from "rxjs/index";
import { Employee } from '../model/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private _httpClient: HttpClient) { }

  private url: string = environment.backendUrl;

  getListOfAllEmployees(): Observable<Employee[]> {
    return this._httpClient.get<Employee[]>(this.url + '/api/employees');
  }

  getEmployeeById(id: number): Observable<any> {
    return this._httpClient.get(this.url+'/api/employees/'+id);
  }

  createNewEmployee(employee: Employee): Observable<Employee> {
    return this._httpClient.post<Employee>(this.url+'/api/employees/', employee);
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this._httpClient.put<Employee>(this.url+'/api/employees/'+id, employee);
  }

  deleteEmployee(id: number): Observable<any> {
    return this._httpClient.delete<any>(this.url+'/api/employees/'+id);
  }

  getEmployeeByEmail(email: string): Observable<Employee> {
      return this._httpClient.get<Employee>(this.url+'/api/employees/searchByEmail?email=' + email);
  }
}
