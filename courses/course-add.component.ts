import { Component, OnInit } from '@angular/core';
import { CourseService } from '../services/course';
import { UUID } from 'crypto';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';



@Component({
  standalone: true,
  selector: 'app-courses',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './course-add.component.html'
})
export class CourseAddComponent implements OnInit{
    form:any;
    
    constructor(
        private builder: FormBuilder, 
        private service: CourseService, 
        private router: Router
    ){}
    
    ngOnInit(): void {
        this.form = this.builder.group({
            id: [null],
            name: [''],
            description: ['']
        })
    }

    save(){
        this.service.create(this.form.value).subscribe({
            next: () => {
                this.router.navigate(['/courses']);
            },
            error: (err) => {
                console.error("Blad podczas dodawania kursu", err);
            }
        });
    }    

}