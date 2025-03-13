package io.sample.playground.patterns.structural.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CompositePatternMain {

    public static void main(String[] args) {
        Department devDepartment = new DevDepartment();
        Department devopsDepartment = new DevopsDepartment();
        HRDepartment hrDepartment = new HRDepartment();

        List<Department> list = new ArrayList<>();
        list.add(devDepartment);
        list.add(devopsDepartment);

        HeadDepartment headDepartment = new HeadDepartment(list);

        headDepartment.printDepartmentName();

        log.info("Adding HR Department");
        headDepartment.addDepartment(hrDepartment);

        headDepartment.printDepartmentName();
    }
}
