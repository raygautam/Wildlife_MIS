//package com.wildlife.mis.service;
//
//import in.gov.meghalayaonline.dto.DepartmentDTO;
//import in.gov.meghalayaonline.model.Department;
//import in.gov.meghalayaonline.repository.DepartmentRepository;
//import in.gov.meghalayaonline.repository.ServicesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DepartmentService {
//    @Autowired
//    DepartmentRepository departmentRepository;
//    @Autowired
//    ServicesRepository servicesRepository;
//
//    public List<Department> getAllDepartments() {
//        List<Department> departments = departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//
//        return departments.stream()
//                .map(department -> new Department(
//                        department.getId(),
//                        department.getName(),
//                        servicesRepository.findByDepartmentId(department.getId())))
//                .toList();
//        // return departments;
//    }
//
//    public Department addDepartment(DepartmentDTO departmentDTO) {
//        Department department = new Department();
//        department.setName(departmentDTO.name());
//        department = departmentRepository.save(department);
//        return department;
//    }
//
//}
