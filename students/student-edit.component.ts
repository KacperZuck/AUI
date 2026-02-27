import { Component, OnInit } from "@angular/core";
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { ActivatedRoute, RouterModule } from "@angular/router";
import { Router } from "@angular/router";
import { StudentService } from "../services/student";
import { UUID } from "crypto";
import { CommonModule } from "@angular/common";

@Component({
  standalone: true,
  selector: 'app-students',
  imports: [RouterModule, CommonModule, ReactiveFormsModule],
  templateUrl:'./student-edit.component.html',
  styleUrl: './students.css',
})
export class StudentEditComponent implements OnInit {
  studentId!: UUID;
  form: any;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private service: StudentService,
    private router: Router
  ) {}

  ngOnInit() {
    
    this.form = this.fb.group({
        id: [null],
        fullname: [''],
        grade: [0.0]
    });


    this.studentId = this.route.snapshot.params['studentId'];

    this.service.get(this.studentId).subscribe(student => {
      this.form.patchValue(student)
    });
  }

  update() {
    this.service.update(this.studentId ,this.form.value).subscribe({
    next: () => {
      this.router.navigate(['/students']);
    },
    error: (err) => {
      console.error("Błąd podczas aktualizacji studenta:", err);
      alert("Nie udało się zaktualizować danych studenta. Sprawdź, czy serwer akceptuje metodę PUT.");
      // Tutaj możesz też dodać logikę wyświetlania błędu użytkownikowi.
    }
  }
    );
  }
}


/*
{
            next: () => {
                this.router.navigate(['/students', this.studentId, '/edit']);
            },
            error: (err) => {
                console.error("Blad podczas dodawania kursu", err);
            }
        }
            */
