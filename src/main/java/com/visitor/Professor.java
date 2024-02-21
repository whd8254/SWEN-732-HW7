package com.visitor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Professor implements Element {
    @JsonProperty("Professor name")
    private String name;
    @JsonProperty("Professor email")
    private String email;
    @JsonProperty("Courses")
    private List<String> courseCodes;

    private List<Course> courses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<String> getCourseCodes() {
        return this.courseCodes;
    }

    public Professor () {

    }

    public boolean addCourse(Course course) {
        for (Course c: courses) {
            if (c == course) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean dropCourse(Course course) {
        for (Course c: courses) {
            if (c == course) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String print() {
        return toString();
    }

    /**
     * Not the best way to go from object to json as jackson has ways to do 90% of the work for you but this allows the visitor pattern to shine
     * @return A Json representation of this object
     */
    @Override
    public String save() {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"Professor name\": \"").append(name).append("\",\n");
        json.append("  \"Professor email\": \"").append(email).append("\",\n");
        json.append("  \"Courses\": [");
        for (int i = 0; i < courses.size(); i++) {
            json.append("\"").append(courses.get(i).getCode()).append("\"");
            if (i < courses.size() - 1) {
                json.append(", ");
            }
        }
        json.append("]\n");
        json.append("}");
        return json.toString();
    }

    @Override
    public String accept(Visitor visitor) {
        return (visitor.visitProfessor(this));

    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }
}
