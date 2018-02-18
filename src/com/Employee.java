package com;

/**
 * Created by cleotracey on 2017-03-24.
 */

import java.util.List;

public class Employee {
    private int eid;
    private String name;
    private String empType;


    public Employee (int eid, String name, String empType) {
        this.eid = eid;
        this.name = name;
        this.empType = empType;

    }

    public Employee() {

    }

    public Employee(String name, String empType) {
        this.name = name;
        this.empType = empType;

    }

    public int getEid() {
        return eid;
    }

    public String getName() {
        return name;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

}