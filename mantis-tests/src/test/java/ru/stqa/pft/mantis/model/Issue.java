package ru.stqa.pft.mantis.model;

/**
 * Created by Golem on 05.05.2017.
 */
public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;
    private int state;
    private String state_name;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withState(int state){
        this.state = state;
        return this;
    }

    public int getState() {
        return state;
    }

    public String getState_name() {
        return state_name;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withStateName(String stateName){
        this.state_name = stateName;
        return this;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
