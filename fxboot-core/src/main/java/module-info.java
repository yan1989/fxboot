module fxboot.core {
 requires spring.core;
 requires spring.context;
 requires javafx.fxml;
 requires javafx.graphics;
 requires spring.beans;
 requires org.slf4j;
 requires spring.boot;
 exports com.yan.fxboot;
 exports com.yan.fxboot.annotation;
 exports com.yan.fxboot.view;
}