package com.yan.fxboot.common;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class UI {

 private static Scene scene;

 private static Stage stage;

 private static String title;

 public static Scene getScene() {
  return scene;
 }

 public static void setScene(Scene scene) {
  UI.scene = scene;
 }

 public static Stage getStage() {
  return stage;
 }

 public static void setStage(Stage stage) {
  UI.stage = stage;
 }

 public static String getTitle() {
  return title;
 }

 public static void setTitle(String title) {
  UI.title = title;
 }


}
