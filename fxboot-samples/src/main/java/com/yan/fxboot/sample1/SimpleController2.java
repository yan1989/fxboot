package com.yan.fxboot.sample1;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class SimpleController2 implements Initializable {

 @FXML
 Button button;
 @FXML
 Label label;
 @Resource
 SimpleController simpleController;

 public void sayHello(Event event) {
  Platform.runLater(() -> {
   simpleController.label.setText("hello222");
  });
 }

 @Override
 public void initialize(URL location, ResourceBundle resources) {
 }
}
