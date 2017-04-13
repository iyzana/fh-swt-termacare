package de.adesso.termacare;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application {

    public static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
