package com.visitor;

public class PrintVisitor implements Visitor {
    @Override
    public String visitStudent(Student student) {
        return student.print();
    }

    @Override
    public String visitProfessor(Professor professor) {
        return professor.print();
    }

    @Override
    public String visitCourse(Course course) {
        return course.print();
    }
}
