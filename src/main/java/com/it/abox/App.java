package com.it.abox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	
	/** 这是一个web应用，使用了嵌入式的tomcat
	    package后：java -jar abox-0.0.1-SNAPSHOT.jar
	*/
    public static void main( String[] args ){
        SpringApplication.run(App.class,  "javacDefaultEncoding=UTF-8");
        /*MyConfig my = new MyConfig();
        System.out.println(my.getPort());*/
    }
}
