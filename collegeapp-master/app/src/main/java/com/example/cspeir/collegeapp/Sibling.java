package com.example.cspeir.collegeapp;

/**
 * Created by cspeir on 10/6/2017.
 */

public class Sibling  extends FamilyMember {

    public Sibling(String first, String last){
        super(first, last);

    }
    public Sibling(){
        super();

    }


    @Override
    public String toString(){
        return "Sibling: "+ getFirstName() + " "+ getLastName();
    }

}
