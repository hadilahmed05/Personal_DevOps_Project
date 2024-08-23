import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseUrl = "http://98.71.45.41:8080/students"
  constructor(private httpClient: HttpClient) { }

  getStudentList(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(this.baseUrl);
  }

  addStudent(student: Student): Observable<Object> {
    return this.httpClient.post(this.baseUrl, student);
  }

  getStudentById(id: number): Observable<Student> {
    return this.httpClient.get<Student>(`${this.baseUrl}/${id}`);
  }

  updateStudent(id: number, student: Student): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, student);
  }

  deleteStudent(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}