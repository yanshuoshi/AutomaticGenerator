package com.yss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
public class AutomaticGeneratorApplication {

    public static void main(String[] args) {
//        SpringApplication.run(NewCodeApplication.class, args);
        String path ="../../templates/iot_" + "entity.ftl";
        try {
            File file = ResourceUtils.getFile("/templates/iot_" + "entity.ftl");
            System.out.printf("---------------"+file.exists());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ClassPathResource classPathResource = new ClassPathResource("/templates/iot_" + "entity.ftl");
        System.out.println(classPathResource.getPath());
        File file = new File(classPathResource.getPath());
        System.out.println(file.exists());
    }

}
