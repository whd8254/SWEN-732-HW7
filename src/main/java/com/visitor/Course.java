package com.visitor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course implements Element {
    @JsonProperty("Course name")
    private String name;
    @JsonProperty("Professor name")
    private String professor;
    @JsonProperty("Course code") //Will function as the primary key for the course
    private String code;
    @JsonProperty("Course room")
    private String room;
    @JsonProperty("Course time")
    private String time;

    public Course() {

    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCode() {
        return code;
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
        json.append("  \"Course name\": \"").append(name).append("\",\n");
        json.append("  \"Professor name\": \"").append(professor).append("\",\n");
        json.append("  \"Course code\": \"").append(code).append("\",\n");
        json.append("  \"Course room\": \"").append(room).append("\",\n");
        json.append("  \"Course time\": \"").append(time).append("\"\n");
        json.append("}");
        return json.toString();
    }

    @Override
    public String accept(Visitor visitor) {
        return (visitor.visitCourse(this));
//        return null;
    }

    @Override
    public boolean addCourse(Course course) {
        return false;
    }

    @Override
    public boolean dropCourse(Course course) {
        return false;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", professor='" + professor + '\'' +
                ", code='" + code + '\'' +
                ", room='" + room + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
