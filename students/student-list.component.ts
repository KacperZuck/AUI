import { Component, OnInit } from '@angular/core';
import { UUID } from 'crypto';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { StudentService } from '../services/student';
import { BehaviorSubject } from 'rxjs';


@Component({
  standalone: true,
  selector: 'app-students',
  imports: [CommonModule, RouterModule],
  templateUrl:'./student-list.component.html',
  styleUrl: './students.css',
})
export class StudentsListComponent implements OnInit {

  private studentSubject = new BehaviorSubject<any[]>([]);
  student$ = this.studentSubject.asObservable();

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit() {
    this.studentService.getAll().subscribe({
      next: (data) => {
        console.log("Student loded: ", data);
        this.studentSubject.next(data)
      },
      error: (error) => {
        console.error('Error at loadding students', error);
        this.studentSubject.next([]);
      }
    })
  }

  delete(id: UUID) {
    this.studentService.delete(id).subscribe(() => this.ngOnInit());
  }

  add() {
    this.router.navigate(['/students/add']);
  }

  edit(id: UUID) {
    this.router.navigate(['/students', id, 'edit']);
  }

  details(id: UUID) {
    this.router.navigate(['/students', id]);
  }
  
}
