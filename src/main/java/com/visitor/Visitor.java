package com.visitor;

public interface Visitor {

    public String visitStudent(Student student);
    public String visitProfessor(Professor professor);
    public String visitCourse(Course course);
}
