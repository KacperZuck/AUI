import { Component, OnInit } from '@angular/core';
import { CourseService } from '../services/course';
import { UUID } from 'crypto';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';



@Component({
  standalone: true,
  selector: 'app-courses',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl:'./course-edit.component.html',
  styleUrl: './courses.css',
})
export class CourseEditComponent implements OnInit {
    
    constructor(private builder: FormBuilder, private route: ActivatedRoute,
        private service: CourseService, private router: Router){}

    courseId!: UUID;
    form: any;

    ngOnInit(){
        this.form = this.builder.group({
            id: [null],
            name: [''],
            description: ['']
        });

        this.courseId = this.route.snapshot.params['id'];
        this.service.get(this.courseId).subscribe( course => { this.form.patchValue(course)});
    }

    update(){
        this.service.update(this.courseId,this.form.value).subscribe(
            () => this.router.navigate(['/courses']));
    }    

}