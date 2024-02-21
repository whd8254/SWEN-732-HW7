package com.visitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class UI {
    List<Student> students;
    List<Professor> professors;
    List<Course> courses;


    public static void main(String[] args) throws IOException {
        new UI();
    }
    private static <T> T convertJsonToObject(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, typeReference);
    }
    public UI () throws IOException {

        Path sPath = Paths.get("src/main/json/students.json");
        Path pPath = Paths.get("src/main/json/professors.json");
        Path cPath = Paths.get("src/main/json/courses.json");

//        courses = Arrays.asList(objectMapper.readValue(cFile, Course[].class));
//        students = Arrays.asList(objectMapper.readValue(sFile, Student[].class));
//        professors = Arrays.asList(objectMapper.readValue(pFile, Professor[].class));
        courses = convertJsonToObject(Files.readString(cPath), new TypeReference<List<Course>>() {});
        students = convertJsonToObject(Files.readString(sPath), new TypeReference<List<Student>>() {});
        professors = convertJsonToObject(Files.readString(pPath), new TypeReference<List<Professor>>() {});

        /**
         * This section puts the actual cource objects into the student and proffessors instead of the cource codes
         */
        Course course;
        for (Student student : students) {
            for (String code: student.getCourseCodes()) {
                course = findCourse(code);
                if(course != null) {
                    student.addCourse(course);
                }
            }
        }
        for (Professor professor : professors) {
            for (String code: professor.getCourseCodes()) {
                course = findCourse(code);
                if(course != null) {
                    professor.addCourse(course);
                }
            }
        }
        login();
    }

    public Course findCourse(String code) {
        for (Course c : courses) {
            if(c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
    public void login() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("To login to the system type your name: ");
            String userName = scanner.nextLine();

            /**
             * Not proud of this login if time is found it will be done better
             */
            for (Student student : students) {
                if (userName.equals(student.getName())) {
                    Student curUser = student;
                    addDropCources(curUser);
                }
            }
            for (Professor professor : professors) {
                if (userName.equals(professor.getName())) {
                    Professor curUser = professor;
                    addDropCources(curUser);
                }
            }
        }
    }

    public void addDropCources(Element curUser) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add course");
            System.out.println("2. Drop course");
            System.out.println("3. View courses available");
            System.out.println("4. Print personal info");
            System.out.println("5. Save changes");
            System.out.println("6. logout");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            PrintVisitor pVisitor = new PrintVisitor();
            SaveVisitor sVisitor = new SaveVisitor();

            switch (choice) {
                case 1: //Add course
                    System.out.println("Enter the course you wish to add: ");
                    String courseToAdd = scanner.nextLine();
                    curUser.addCourse(findCourse(courseToAdd));
                    break;
                case 2: //Drop course
                    System.out.println("Enter the course you wish to drop: ");
                    String courseToDrop = scanner.nextLine();
                    curUser.dropCourse(findCourse(courseToDrop));
                case 3: //View courses available
                    for (Course course : courses) {
                        System.out.println(course.accept(pVisitor));
                    }
                    break;
                case 4: //Print personal info
                    System.out.println(curUser.accept(pVisitor));
                    break;
                case 5: //Save changes
                    List<String> jsonCourses = new ArrayList<>();
                    for (Course course : courses) {
                        jsonCourses.add(course.accept(sVisitor));
                    }
                    List<String> jsonStudents = new ArrayList<>();
                    for (Student student : students) {
                        jsonStudents.add(student.accept(sVisitor));
                    }
                    List<String> jsonProfessors = new ArrayList<>();
                    for (Professor professor : professors) {
                        jsonProfessors.add(professor.accept(sVisitor));
                    }

                    toJson("src/main/json/courses.json", jsonCourses);
                    toJson("src/main/json/students.json", jsonStudents);
                    toJson("src/main/json/professors.json", jsonProfessors);
                    break;
                case 6: //Logout
                    return;
            }
        }
    }

    public void toJson(String fileName, List<String> fileContents) {
        StringBuilder combinedJson = new StringBuilder("[");
        for (int i = 0; i < fileContents.size(); i++) {
            combinedJson.append(fileContents.get(i));
            if (i < fileContents.size() - 1) {
                combinedJson.append(",");
            }
        }
        combinedJson.append("]");

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(combinedJson.toString());
            System.out.println("Data written to " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
