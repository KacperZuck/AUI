import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UUID } from 'crypto';

@Injectable({
  providedIn: 'root',
})

export class CourseService {
  private api ='http://localhost:8080/api/courses';
  
  constructor(private http: HttpClient){}

getAll(){
    return this.http.get<any[]>(this.api);
  }

  get(id: UUID){
    return this.http.get<any>(`${this.api}/${id}`);
  }

  create(data: any){
    return this.http.post(this.api, data);
  }

  update(id: UUID, data: any){
    return this.http.put(`${this.api}/${id}`, data);
  }

  delete(id: UUID){
    return this.http.delete(`${this.api}/${id}`);
  }
}
