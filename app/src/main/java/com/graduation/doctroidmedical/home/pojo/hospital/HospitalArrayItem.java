package com.graduation.doctroidmedical.home.pojo.hospital;

import java.util.List;

public class HospitalArrayItem {
    private String id;
    private String name;
    private String picture;
    private List<Department> departmentList;

    public HospitalArrayItem(String id, String name, String picture, List<Department> departmentList) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.departmentList = departmentList;
    }

    public String getId() {
        return id;
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
