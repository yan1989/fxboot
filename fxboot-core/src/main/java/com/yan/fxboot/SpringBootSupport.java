package com.yan.fxboot;

import com.yan.fxboot.common.UI;
import com.yan.fxboot.view.AbstractFXMLView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CompletableFuture;

public abstract class SpringBootSupport extends Application {

 private final Logger logger = LoggerFactory.getLogger(SpringBootSupport.class);
 private static ConfigurableApplicationContext applicationContext;
 private static Class<? extends AbstractFXMLView> initialView;
 private CompletableFuture<Runnable> loadingFuture;

 protected SpringBootSupport() {
  loadingFuture = new CompletableFuture<>();
 }


 @Override
 public void start(Stage primaryStage) {
  UI.setStage(primaryStage);
  loadingFuture.complete(() -> {
   logger.info("Show initialView...");
   showView(initialView);
  });
 }

 public static void showView(Class<? extends AbstractFXMLView> view) {
  AbstractFXMLView abstractFXMLView = applicationContext.getBean(view);
  if (UI.getScene() == null) {
   UI.setScene(new Scene(abstractFXMLView.getRoot()));
  } else {
   UI.getScene().setRoot(abstractFXMLView.getRoot());
  }
  UI.getStage().setScene(UI.getScene());
  UI.getStage().show();
 }

 public static void showView(Class<? extends AbstractFXMLView> view, Modality modality) {
  AbstractFXMLView abstractFXMLView = applicationContext.getBean(view);
  Stage newStage = new Stage();
  Scene newScene;
  if (abstractFXMLView.getRoot().getScene() != null) {
   newScene = abstractFXMLView.getRoot().getScene();
  } else {
   newScene = new Scene(abstractFXMLView.getRoot());
  }

  newStage.setScene(newScene);
  newStage.initModality(modality);
  newStage.initOwner(UI.getStage());
  newStage.setTitle("app");
  newStage.show();
 }

 @Override
 public void init() {
  logger.info("init FxBoot...");
  CompletableFuture.supplyAsync(() -> SpringApplication.run(this.getClass())).whenComplete((ctx, throwable) -> {
   if (throwable != null) {
    logger.error("init failed:", throwable);
   } else {
    applicationContext = ctx;
    logger.info("FxBoot inited");
   }
  }).thenAcceptBothAsync(loadingFuture, (ctx, showInitialView) -> Platform.runLater(showInitialView));
 }

 protected static void runApp(final Class<? extends Application> mainClass, Class<? extends AbstractFXMLView> view, String[] args) {
  initialView = view;
  launch(mainClass, args);
 }

}
