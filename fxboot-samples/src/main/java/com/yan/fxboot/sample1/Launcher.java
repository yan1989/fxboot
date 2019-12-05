package com.yan.fxboot.sample1;

import com.yan.fxboot.SpringBootSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher extends SpringBootSupport {

 public static void main(String[] args) {
  runApp(Launcher.class, SimpleView.class, args);
 }

}
