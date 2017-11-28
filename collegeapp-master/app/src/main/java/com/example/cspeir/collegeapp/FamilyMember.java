package com.example.cspeir.collegeapp;

/**
 * Created by cspeir on 10/6/2017.
 */

public abstract class FamilyMember extends ApplicantData {
    public static final String EXTRA_RELATION="org.pltw.examples.collegeapp.relation";
    public static final String EXTRA_INDEX= "org.pltw.examples.collegeapp.index";
    public static final String EXTRA_CONTENT = "org.pltw.examples.collegeapp.content";
    private String firstName;
    private String lastName;
    private String email;
    private String objectId;
    public String getFirstName() {
        return firstName;
    }

    public boolean equals(FamilyMember familyMember){
        if (familyMember instanceof Guardian && this instanceof Guardian){
            Guardian g = (Guardian)familyMember;

        if (g.getFirstName()==this.getFirstName()&& g.getLastName()==this.getLastName()){
            return true;
        }
        else{
            return false;
        }
        }
        else if (familyMember instanceof Sibling&& this instanceof Sibling){
            Sibling s = (Sibling)familyMember;
            if (s.getFirstName()==this.getFirstName()&& s.getLastName()==this.getLastName()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public FamilyMember(){
        firstName="";
        lastName = "";
        email = "cospe18@hotmail.com";

    }
    public FamilyMember (String first, String last){
        this.firstName = first;
        this.lastName = last;

    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
