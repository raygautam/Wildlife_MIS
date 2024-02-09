//package com.wildlife.mis.service;
//
//import in.gov.meghalayaonline.model.Servicetype;
//import in.gov.meghalayaonline.repository.ServicesTypeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ServiceTypeService {
//    @Autowired
//    ServicesTypeRepository servicesTypeRepository;
//
//    public List<Servicetype> getAllServiceTypes() {
//        List<Servicetype> servicetypes = servicesTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//        return servicetypes;
//    }
//}
