import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { ActivatedRoute, RouterModule } from "@angular/router";
import { Router } from "@angular/router";
import { StudentService } from "../services/student";
import { Comment } from "@angular/compiler";
import { Component, OnInit } from "@angular/core";
import { UUID } from "crypto";
import { CommonModule } from "@angular/common";
import { CourseService } from "../services/course";

@Component({
  standalone: true,
  selector: 'app-students',
  imports: [RouterModule, CommonModule, ReactiveFormsModule],
  templateUrl:'./student-add.component.html',
  styleUrl: './students.css',
})
export class StudentAddComponent implements OnInit {
  courseId!: UUID;
  form: any;

  constructor(
    private builder: FormBuilder,
    private route: ActivatedRoute,
    private studentService: StudentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.form = this.builder.group({
      id: [null],
      fullname: [''],
      grade: [0.0]
    });

  this.courseId = this.route.snapshot.params['courseId'];
  }

  save() {
    this.studentService.create(this.courseId, this.form.value).subscribe({
      next: () => {
        this.router.navigate(['/courses', this.courseId]);
      },
      error: (err) => {
        console.error("Blad podczas dodawania studenta do kursu", err);
      }
    });
  }
}
