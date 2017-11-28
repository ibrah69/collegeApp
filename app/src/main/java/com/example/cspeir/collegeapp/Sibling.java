package com.example.cspeir.collegeapp;

public class Sibling extends FamilyMember {

    public Sibling (){
        super();
        super.setEmail("defensedude31@gmail.com");
    }
    public Sibling (String firstName, String lastName){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        super.setEmail("defensedude31@gmail.com");
    }

    public String toString(){
        String output = "Sibling: " + this.getFirstName() + " " + this.getLastName();
        return output;
    }


}