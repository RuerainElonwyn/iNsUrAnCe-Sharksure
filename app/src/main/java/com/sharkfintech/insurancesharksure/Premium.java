package com.sharkfintech.insurancesharksure;

/**
 * Created by Orcas on 30/7/2016.
 */
public class Premium {
    private String name;
    private String description;
    public Premium(String name, String description){
        this.name = name;
        this.description = description;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
