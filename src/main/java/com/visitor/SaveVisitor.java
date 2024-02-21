package com.visitor;

public class SaveVisitor implements Visitor {
    @Override
    public String visitStudent(Student student) {
        return student.save();
    }

    @Override
    public String visitProfessor(Professor professor) {
        return professor.save();
    }

    @Override
    public String visitCourse(Course course) {
        return course.save();
    }
}
