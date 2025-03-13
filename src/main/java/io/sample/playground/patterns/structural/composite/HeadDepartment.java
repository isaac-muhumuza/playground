package io.sample.playground.patterns.structural.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HeadDepartment implements Department {

    List<Department> departments = new ArrayList<>();

    public HeadDepartment(List<Department> departments) {
        this.departments = departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    @Override
    public void printDepartmentName() {
        departments.forEach(Department::printDepartmentName);
    }
}
