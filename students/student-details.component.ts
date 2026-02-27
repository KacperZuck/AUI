// student-details.component.ts

import { Component, NgZone, OnInit } from "@angular/core";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { StudentService } from "../services/student";
import { UUID } from "crypto";
import { CommonModule } from "@angular/common";

import { Observable, of, switchMap } from 'rxjs'; // Zostawiamy of, jeśli uzywamy go w switchMap
type Guid = `${string}-${string}-${string}-${string}-${string}`;
@Component({
  standalone: true,
  selector: 'app-student-details', // Upewnij się, że selektor jest unikalny
  imports: [RouterModule, CommonModule],
  templateUrl:'./student-details.html',
  styleUrl: './students.css',
})
export class StudentDetailsComponent implements OnInit {
  
  student$!: Observable<any>;
  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService,
    private router: Router 
  ) {}

  ngOnInit() {
    this.student$ = this.route.paramMap.pipe(
      switchMap(params => {
        const studentId = params.get('studentId');
        if (studentId) {
          const idAsGuid = studentId as Guid; 

        return this.studentService.get(idAsGuid);
        }
        return of(null);
      })
    );
  }

  
}
