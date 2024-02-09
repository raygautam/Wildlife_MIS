//package com.wildlife.mis.util;
//
//
//import com.wildlife.mis.service.DatabaseCreationService;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DatabaseInitializer implements ApplicationRunner {
//
//    private final DatabaseCreationService databaseCreationService;
//
//    public DatabaseInitializer(DatabaseCreationService databaseCreationService) {
//        this.databaseCreationService = databaseCreationService;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) {
//        databaseCreationService.createDatabase("dynamic_database");
//    }
//}
//
