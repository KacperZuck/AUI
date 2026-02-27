import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UUID } from 'crypto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  
  
  getAllByCourse(courseId: UUID) {
    return this.http.get<any[]>(`${this.api}/courses/${courseId}`);
    }
  
  private api ='http://localhost:8080/api/students';

  constructor(private http: HttpClient){}

  getAll(){
    return this.http.get<any[]>(this.api);
  }

  get(id: UUID): Observable<any>{
    return this.http.get<any>(`${this.api}/${id}`);
  }

  create(courseId: UUID, data: any){
    return this.http.post(`${this.api}/courses/${courseId}`, data);
  }

  update(id: UUID, data: any): Observable<any>{
    return this.http.put(`${this.api}/${id}`, data);
  }

  delete(id: UUID){
    return this.http.delete(`${this.api}/${id}`);
  }













}
