package org.example;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.util.concurrent.ForkJoinPool;


public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {

        Course course1 = Course.builder()
                .id(1L)
                .name("Programming")
                .build();

        Course course2 = Course.builder()
                .id(2L)
                .name("Data Structures")
                .build();

        Student s1 = Student.builder()
                .id(1L)
                .fullName("Alice Johnson")
                .grade(4.5)
                .course(course1)
                .build();

        Student s2 = Student.builder()
                .id(2L)
                .fullName("Bob Smith")
                .grade(3.8)
                .course(course1)
                .build();

        Student s3 = Student.builder()
                .id(3L)
                .fullName("Charlie Brown")
                .grade(4.2)
                .course(course2)
                .build();

        List<Course> courses = List.of(course1, course2);

        course1.getStudents().addAll(List.of(s1, s2));
        course2.getStudents().add(s3);


        courses.forEach(course -> {
            System.out.println(course);
            course.getStudents().forEach(student -> System.out.println("  " + student));
        });
        System.out.println();

        // z3

        Set<Student> allStudents = courses.stream()
                .flatMap( course -> course.getStudents().stream()).collect(Collectors.toSet());

        allStudents.stream().forEach(System.out::println);
        System.out.println();

        // z4
        allStudents.stream()
                .filter(student -> student.getGrade() > 4.0)
                .sorted((a, b) -> a.getFullName().compareTo(b.getFullName()))
                .forEach(System.out::println);

        System.out.println();

        // z5

        List<StudentDTO> dtos = allStudents.stream()
                .filter(Objects::nonNull)
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getFullName(),
                        student.getGrade(),
                        student.getCourse() != null ? student.getCourse().getName() : "Not enroll"
                ))
                .sorted(Comparator.comparing(
                        StudentDTO::getFullName,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                ))
                .collect(Collectors.toList());


        dtos.stream().forEach(System.out::println);
        System.out.println();

        // z6

        try(ObjectOutputStream o1 = new ObjectOutputStream(new FileOutputStream("courses.txt"))){
            o1.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream o2 = new ObjectInputStream(new FileInputStream("courses.txt"))){
            List<Course> loadedCos = (List<Course>) o2.readObject();
            loadedCos.forEach(course -> {
                System.out.println(course);
                course.getStudents().forEach( student -> System.out.println(" " + student));
            });
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println();

        // z7

        ForkJoinPool customPool = new ForkJoinPool(2); // wątki
        try {
            customPool.submit(() ->
                    courses.parallelStream()
                            .forEach(course -> {
                                System.out.println("Course: " + course.getName());
                                course.getStudents().forEach(student -> {
                                    System.out.println("  " + student.getFullName());
                                    try {
                                        Thread.sleep(1500); // symulacja pracy
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                });
                            })
            ).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customPool.shutdown();
        }

    }
}
