package de.adesso.termacare.gui.construct;

import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@NoArgsConstructor
public abstract class AbstractView<T extends AbstractController> implements View {
    
    protected T controller;
    @Getter
    private Stage stage;
    @Getter
    protected Scene scene;
    private ResourceBundle resources;
    
    public AbstractView(T controller, Stage stage, Scene scene) {
        this.controller = controller;
        this.stage = stage;
        this.scene = scene;
    
        resources = ResourceBundle.getBundle("languages", controller.getLanguageSelection().getLocale());
    }
    
    @Override
    public void show() {
        setStyleClasses();
        registerListener();
        fillComponentsWithSelectedLanguage();
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void fillComponentWithText(Labeled labeled, String nameInBundle) {
        try {
            labeled.setText(resources.getString(nameInBundle));
            log.debug("The " + labeled.getClass().getSimpleName() + " with the CSS-ID=\"" + labeled.getId() + "\" is filled with \"" + labeled.getText() + "\" in class " + this.getClass().getName());
        } catch (MissingResourceException e) {
            labeled.setText(nameInBundle);
            log.error("Can not find \"" + nameInBundle + "\" in ResourceBundle for " + this.getClass().getName(), e);
        }
    }
}
