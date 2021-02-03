package com.csc.ems.models;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    private String degree_id;
    private String degreeName;
    private String student_id;
    private String project_title;
    private String project_describe;
    private String project_tools;
    private String project_methodology;
    private String project_objective;
    private String project_status;
    private String project_field;

    public UserData() {
        // for firebase connection
    }

    // adding data to object by constructor
    public UserData(String degree_id, String degreeName, String student_id, String project_title, String project_describe,
                    String project_tools, String project_methodology,
                    String project_objective, String project_status, String project_field) {
        this.degree_id = degree_id;
        this.degreeName = degreeName;
        this.student_id = student_id;
        this.project_title = project_title;
        this.project_describe = project_describe;
        this.project_tools = project_tools;
        this.project_methodology = project_methodology;
        this.project_objective = project_objective;
        this.project_status = project_status;
        this.project_field = project_field;
    }

    public Map<String,Object> toMap(){

        HashMap<String,Object> result = new HashMap<>();
        result.put(degreeName , degree_id);
        result.put("student_id" , student_id);
        result.put("project_title" , project_title);
        result.put("project_describe" , project_describe);
        result.put("project_tools" , project_tools);
        result.put("project_methodology" , project_methodology);
        result.put("project_objective" , project_objective);
        result.put("project_status" , project_status);
        result.put("project_field" , project_field);

        return result;
    }

    public String getDegree_id() {
        return degree_id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getProject_title() {
        return project_title;
    }

    public String getProject_describe() {
        return project_describe;
    }

    public String getProject_tools() {
        return project_tools;
    }

    public String getProject_methodology() {
        return project_methodology;
    }

    public String getProject_objective() {
        return project_objective;
    }

    public String getProject_status() {
        return project_status;
    }

    public String getProject_field() {
        return project_field;
    }
}
