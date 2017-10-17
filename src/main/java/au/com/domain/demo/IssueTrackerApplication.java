package au.com.domain.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import au.com.domain.demo.control.IssueTrackingController;


@SpringBootApplication
@ComponentScan("au.com.domain.demo")
public class IssueTrackerApplication {

    public static void main(final String[] args) {
    	System.out.println("coming here.0000..");
        SpringApplication.run(IssueTrackerApplication.class, args);
    }
}
