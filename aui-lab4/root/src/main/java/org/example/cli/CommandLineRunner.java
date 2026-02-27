package org.example.cli;

import jakarta.annotation.PostConstruct;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineRunner {

    private final CourseService courseService;
    private final StudentService studentService;

    public CommandLineRunner(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @PostConstruct
    public void start() {
        new Thread(this::runCLI).start();
    }

    private void runCLI() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printHelp();

        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            switch (input.toLowerCase()) {
                case "help":
                    printHelp();
                    break;

                case "list courses":
                    List<Course> courses = courseService.findAll();
                    courses.forEach(course -> System.out.println(course.getId() + " - " + course.getName()));
                    break;

                case "list students":
                    List<Student> students = studentService.findAll();
                    students.forEach(student -> System.out.println(
                            student.getId() + " - " + student.getFullName() +
                                    " (" + student.getCourse().getName() + ")"
                    ));
                    break;

                case "add student":
                    addStudent(scanner);
                    break;

                case "delete student":
                    deleteStudent(scanner);
                    break;

                case "exit":
                    System.out.println("Stopping application...");
                    running = false;
                    System.exit(0);
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' to see available commands.");
            }
        }
    }

    private void printHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("  help           - Show this help");
        System.out.println("  list courses   - List all courses");
        System.out.println("  list students  - List all students");
        System.out.println("  add student    - Add new student");
        System.out.println("  delete student - Delete existing student");
        System.out.println("  exit           - Stop the application");
    }

    private void addStudent(Scanner scanner) {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter grade: ");
        double grade = Double.parseDouble(scanner.nextLine());

        List<Course> courses = courseService.findAll();
        System.out.println("Select course by number:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + 1 + ") " + courses.get(i).getName());
        }
        int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Course selectedCourse = courses.get(courseIndex);

        Student newStudent = Student.builder()
                .id(UUID.randomUUID())
                .fullName(fullName)
                .grade(grade)
                .course(selectedCourse)
                .build();

        studentService.addToCourse(selectedCourse.getId(), newStudent);
        System.out.println("Student added successfully!");
    }

    private void deleteStudent(Scanner scanner) {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("No students to delete.");
            return;
        }

        System.out.println("Select student to delete by number:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + 1 + ") " + students.get(i).getFullName() + " (" + students.get(i).getCourse().getName() + ")");
        }
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Student studentToDelete = students.get(index);


        //
        studentService.findById(studentToDelete.getId()).ifPresent(student -> {
            Course course = student.getCourse();
            if (course != null) {
                course.getStudents().remove(student); // usuń powiązanie
            }
            studentService.delete(studentToDelete.getId());
        });

        System.out.println("Student deleted successfully!");
        students = studentService.findAll();
        students.forEach(student -> System.out.println(
                student.getId() + " - " + student.getFullName() +
                        " (" + student.getCourse().getName() + ")"
        ));
    }

}
