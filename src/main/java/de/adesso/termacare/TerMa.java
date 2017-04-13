package de.adesso.termacare;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

public class TerMa extends Application {

    public static org.slf4j.Logger logger = LoggerFactory.getLogger(TerMa.class);

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        Application.launch(TerMa.class, args);
    }
}
