package com.graduation.doctroidmedical.home.pojo.hospital;

import java.util.List;

public class HospitalArrayItem {
    private String name;
    private String picture;
    private List<Department> departmentList;

    public HospitalArrayItem(String name, String picture, List<Department> departmentList) {
        this.name = name;
        this.picture = picture;
        this.departmentList = departmentList;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }
}
