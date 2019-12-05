package com.yan.fxboot.sample1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class SimpleController implements Initializable {

 @Resource
 SimpleController2 simpleController2;

 @FXML
 Button button;
 @FXML
 Label label;


 public void sayHello(Event event) {
  Launcher.showView(SimpleView2.class, Modality.WINDOW_MODAL);
 }

 public void setLabel(String label) {
  System.out.println(this.label);
  this.label.setText(label);
 }

 @Override
 public void initialize(URL location, ResourceBundle resources) {
 }
}
