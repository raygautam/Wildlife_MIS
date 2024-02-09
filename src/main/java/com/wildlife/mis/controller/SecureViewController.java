//package com.wildlife.mis.controller;
//
//import in.gov.meghalayaonline.dto.DepartmentDTO;
//import in.gov.meghalayaonline.dto.ServicesDTO;
//import in.gov.meghalayaonline.service.DepartmentService;
//import in.gov.meghalayaonline.service.ServiceTypeService;
//import in.gov.meghalayaonline.service.ServicesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/secure/")
//public class SecureViewController {
//
//    @Autowired
//    DepartmentService departmentService;
//    @Autowired
//    ServicesService servicesService;
//    @Autowired
//    ServiceTypeService serviceTypeService;
//
//    @GetMapping("home")
//    public String home() {
//        return "secure/home";
//    }
//
//    @GetMapping("users")
//    public String users() {
//        return "secure/users";
//    }
//
//    @GetMapping("departments")
//    public String departments(Model model) {
//        model.addAttribute("departments", departmentService.getAllDepartments());
//        DepartmentDTO departmentDTO = new DepartmentDTO(null);
//        model.addAttribute("departmentDTO", departmentDTO);
//
//        return "secure/departments";
//    }
//
//    @PostMapping(path = "department")
//    public String departments(DepartmentDTO departmentDTO) {
//
//        departmentService.addDepartment(departmentDTO);
//        return "redirect:/secure/departments";
//    }
//
//    @GetMapping("dashboard")
//    public String dashboard() {
//        return "secure/dashboard";
//    }
//
//    @GetMapping("services")
//    public String services(Model model) {
//
//        model.addAttribute("departments", departmentService.getAllDepartments());
//        model.addAttribute("servicetypes", serviceTypeService.getAllServiceTypes());
//        ServicesDTO servicesDTO = new ServicesDTO(null, null, null, null, null, null);
//        model.addAttribute("servicesDTO", servicesDTO);
//        return "secure/services";
//    }
//
//    @PostMapping("service")
//    public String addservices(ServicesDTO servicesDTO) {
//
//        servicesService.addServices(servicesDTO);
//        return "redirect:/secure/services";
//    }
//}