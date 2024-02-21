package com.visitor;

public interface Element {

//    public boolean addCourse(Course course);
//    public boolean removeCourse(Course course);
    public String print();
    public String save();
    public String accept(Visitor visitor);

    public boolean addCourse(Course course);

    public boolean dropCourse(Course course);
}
