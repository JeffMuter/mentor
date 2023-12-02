package com.techelevator.model;

public class Mentor {
//PRIVATE VARIABLES

    private String name = "";
    private Long id;


    //CONSTRUCTORS
    public Character() {
    }

    public void Character(User creator, String name /*, additional fields */) {
        this.creator = creator;
        this.name = name;
        // Assign other fields
    }


    //GETTERS AND SETTERS


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
