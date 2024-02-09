//package com.wildlife.mis.service;
//
//import in.gov.meghalayaonline.dto.ServicesDTO;
//import in.gov.meghalayaonline.model.Services;
//import in.gov.meghalayaonline.repository.DepartmentRepository;
//import in.gov.meghalayaonline.repository.ServicesRepository;
//import in.gov.meghalayaonline.repository.ServicesTypeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ServicesService {
//    @Autowired
//    ServicesRepository servicesRepository;
//
//    @Autowired
//    DepartmentRepository departmentRepository;
//    @Autowired
//    ServicesTypeRepository servicesTypeRepository;
//
//    public Services addServices(ServicesDTO servicesDTO) {
//
//        Services services = new Services();
//        services.setName(servicesDTO.name());
//        services.setUrl(servicesDTO.url());
//        services.setGuidelines(servicesDTO.guidelines());
//        services.setRequirements(servicesDTO.requirements());
//        System.out.println(servicesDTO.departmentId());
//        services.setDepartment(departmentRepository.getReferenceById(servicesDTO.departmentId()));
//        services.setServicetype(servicesTypeRepository.getReferenceById(servicesDTO.serviceTypeId()));
//        services = servicesRepository.save(services);
//        return services;
//    }
//
//    public List<Services> getAllServices() {
//        List<Services> services = servicesRepository.findAll();
//        return services;
//    }
//
//}
