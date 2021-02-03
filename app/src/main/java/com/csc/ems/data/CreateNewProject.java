package com.csc.ems.data;

import java.util.HashMap;
import java.util.Map;

public class CreateNewProject {
    private String project_id;
    private String student_id;
    private String doctor_id;
    private String assistant_id;

    private String project_title;
    private String project_describe;
    private String project_tools;
    private String project_methodology;
    private String project_objective;
    private String project_field;

    private String doctor_project_status;
    private String assistant_project_status;
    private String admin_project_status;


    public CreateNewProject() {
        //to firebase connection
    }

    public CreateNewProject(String project_id, String student_id, String doctor_id, String assistant_id,
                            String project_title, String project_describe, String project_tools,
                            String project_methodology,String project_objective, String project_field,
                            String doctor_project_status, String assistant_project_status, String admin_project_status) {
        this.project_id = project_id;
        this.student_id = student_id;
        this.doctor_id = doctor_id;
        this.assistant_id = assistant_id;
        this.project_title = project_title;
        this.project_describe = project_describe;
        this.project_tools = project_tools;
        this.project_methodology = project_methodology;
        this.project_objective = project_objective;
        this.project_field = project_field;
        this.doctor_project_status = doctor_project_status;
        this.assistant_project_status = assistant_project_status;
        this.admin_project_status = admin_project_status;
    }

    public Map<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("project_id",project_id);
        result.put("student_id",student_id);
        result.put("doctor_id",doctor_id);
        result.put("assistant_id",assistant_id);

        result.put("project_title",project_title);
        result.put("project_describe",project_describe);
        result.put("project_tools",project_tools);
        result.put("project_methodology",project_methodology);
        result.put("project_objective",project_objective);
        result.put("project_field",project_field);

        result.put("doctor_project_status",doctor_project_status);
        result.put("assistant_project_status",assistant_project_status);
        result.put("admin_project_status",admin_project_status);

        result.put("doctor_comment","");

        return result;
    }

    public String getProject_id() {
        return project_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getAssistant_id() {
        return assistant_id;
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

    public String getProject_field() {
        return project_field;
    }

    public String getDoctor_project_status() {
        return doctor_project_status;
    }

    public String getAssistant_project_status() {
        return assistant_project_status;
    }

    public String getAdmin_project_status() {
        return admin_project_status;
    }
}
