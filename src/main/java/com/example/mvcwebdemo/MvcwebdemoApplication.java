package com.example.mvcwebdemo;



import com.example.mvcwebdemo.JDBC.CrudOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcwebdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcwebdemoApplication.class, args);

        CrudOperations crudOperations = new CrudOperations();
    }



}
