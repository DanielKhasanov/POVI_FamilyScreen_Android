package com.povi.povifamilyselect;

import java.util.ArrayList;

/**
 * Created by Daniel on 11/27/2015.
 */
class Family {
    String familyName;
    ArrayList<FamilyMember> familyMembers;

    public Family(String name) {
        familyName = name;
        familyMembers = new ArrayList<FamilyMember>();
    }

    public String getName() {
        return familyName;
    }

    public void setName(String name) {
        this.familyName = name;
    }

    public ArrayList<FamilyMember> getMembers() {
        return familyMembers;
    }

    public void setSummary(ArrayList<FamilyMember> members) {
        this.familyMembers = members;
    }


    public int getNumberOfMembers() {
        return familyMembers.size();
    }
}
