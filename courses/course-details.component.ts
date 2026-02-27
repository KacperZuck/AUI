import { Component, OnInit } from '@angular/core';
import { CourseService } from '../services/course';
import { UUID } from 'crypto';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { StudentService } from '../services/student';
import { CommonModule } from '@angular/common';
import { BehaviorSubject } from 'rxjs';



@Component({
  standalone: true,
  selector: 'app-courses',
  imports: [CommonModule, RouterModule],
  templateUrl:'./course-details.component.html',
  styleUrl: './courses.css',
})
export class CourseDetailsComponent implements OnInit {
    
    constructor( 
        private route: ActivatedRoute,
        private service: CourseService,
        private router: Router, 
        private studentService: StudentService
    ){}

    courseId!: UUID;
    course: any;
    private studentSubject = new BehaviorSubject<any[]>([]);
    student$ = this.studentSubject.asObservable();

    ngOnInit(){
        this.courseId = this.route.snapshot.params['id'];

        this.service.get(this.courseId).subscribe(data => this.course = data);

        this.studentService.getAll().subscribe({
        next: (data) => {
            console.log("Student loded: ", data);
            const filtered = data.filter(student => student.courseID === this.courseId);
            this.studentSubject.next(filtered)
        },
        error: (error) => {
            console.error('Error at loadding students', error);
            this.studentSubject.next([]);
        }
        })
    }

    deleteStudent(studentId: UUID) {
        this.studentService.delete(studentId).subscribe(() => this.ngOnInit());
    }

    addStudent() {
        this.router.navigate(['/courses', this.courseId, 'students', 'add']);
    }

    editStudent(id: UUID) {
        this.router.navigate(['/students', id, 'edit']);
    }

    detailsStudent(id: UUID) {  
        this.router.navigate(['/students', id]);
    }   

}