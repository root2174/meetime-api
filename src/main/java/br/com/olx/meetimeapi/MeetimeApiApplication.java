package br.com.olx.meetimeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MeetimeApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(MeetimeApiApplication.class, args);
  }
}
