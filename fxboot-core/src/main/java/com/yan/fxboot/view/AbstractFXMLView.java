package com.yan.fxboot.view;

import com.yan.fxboot.annotation.FXMLView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractFXMLView implements ApplicationContextAware {

 private ApplicationContext applicationContext;

 private final FXMLView annotation;

 private final URL resoucesURL;

 private FXMLLoader fxmlLoader;

 protected AbstractFXMLView() {
  this.annotation = getFXMLAnnotation();
  this.resoucesURL = getResourceURL(annotation);
 }

 private FXMLView getFXMLAnnotation() {
  return getClass().getAnnotation(FXMLView.class);
 }

 private URL getResourceURL(final FXMLView annotation) {
  return getClass().getResource(annotation.value());
 }

 private void initFXMLloader() {
  if (fxmlLoader == null) {
   fxmlLoader = new FXMLLoader(resoucesURL);
   fxmlLoader.setControllerFactory(applicationContext::getBean);
   try {
    fxmlLoader.load();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }

 public Parent getRoot() {
  initFXMLloader();
  return fxmlLoader.getRoot();
 }

 @Override
 public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
  if (this.applicationContext == null) {
   this.applicationContext = applicationContext;
  }
 }
}
