import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CourseService } from '../services/course';
import { UUID } from 'node:crypto';
import { BehaviorSubject } from 'rxjs';

@Component({
  standalone: true,
  selector: 'courses-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './course-list.component.html'
})
export class CoursesListComponent implements OnInit {

  private courseSubject = new BehaviorSubject<any[]>([]);
  course$ = this.courseSubject.asObservable();

  constructor(
    private service: CourseService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCourse();
   // this.service.getAll().subscribe(data => {
     // console.log("Courses loaded:", data);
      //this.courses = data;
    //});
  }

  loadCourse(): void{
        this.service.getAll().subscribe({
      next: (data) => {
        console.log("Courses loaded:", data);
        this.courseSubject.next(data)
      },
      error: (error) => {
        console.error('Error loading course:', error);
        this.courseSubject.next([]);
      }
    });
  }

  delete(id: UUID) {
    this.service.delete(id).subscribe(() => this.ngOnInit());
  }

  add() {
    this.router.navigate(['/courses/add']);
  }

  edit(id: UUID) {
    this.router.navigate(['/courses', id, 'edit']);
  }

  details(id: UUID) {
    this.router.navigate(['/courses', id]);
  }
}
