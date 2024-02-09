package com.wildlife.mis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@Controller
public class PublicViewController {
//    @Autowired
//    DepartmentService departmentService;
//    @Autowired
//    ServicesRepository servicesRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
//        List<Services> services=servicesRepository.findAll();
//        model.addAttribute("services", services);
//        model.addAttribute("totalCitizenService", servicesRepository.countByServicetypeId(1));
//        model.addAttribute("totalBusinessService", servicesRepository.countByServicetypeId(2));
//        model.addAttribute("totalOtherService", servicesRepository.countByServicetypeId(3));
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

//    @GetMapping("/services")
//    public String services(Model model) {
//        model.addAttribute("departments", departmentService.getAllDepartments());
//
//        return "services";
//    }

    @GetMapping("/citizenservices")
    public String citizenservices() {
        return "citizenservices";
    }

//    @GetMapping("/businessservices")
//    public String businessservices(Model model) {
//        model.addAttribute("departments", departmentService.getAllDepartments());
//        return "businessservices";
//    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/dashboard/department")
    public String departmentdashboard() {
        return "departmentdashboard";
    }

//    @GetMapping("/login")
//    public String getLogin(@ModelAttribute("user") LoginDTO loginDTO) {
//        return "login";
//    }

    @GetMapping("/trackstatus")
    public String trackstatus() {
        return "trackstatus";
    }

    @GetMapping("/dashboard/department/dashboardReport")
    public String dashboardReport() {
        return "dashboardReport";
    }

}
