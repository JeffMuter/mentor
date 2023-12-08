package com.techelevator.model;

public class Mentor {
//PRIVATE VARIABLES

    private String name = "";
    private Long id;


    //CONSTRUCTORS

    public Mentor(int id, String name) {
this.id = (long) id;
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
