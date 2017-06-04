package de.adesso.termacare.gui.construct;

import de.adesso.termacare.gui.util.LanguageSelection;
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

    public AbstractView(T controller, Stage stage, Scene scene) {
        this.controller = controller;
        this.stage = stage;
        this.scene = scene;
    }
    
    @Override
    public void show() {
        setStyleClasses();
        registerListener();
        fillComponentsWithSelectedLanguage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The {@link #fillComponentWithText(Labeled, String)}-method for all components
     */
    public abstract void fillComponentsWithSelectedLanguage();

    /**
     * This Method fills the component with text
     * @param labeled The component
     * @param nameInBundle The identifier in resource-bundle
     */
    public static void fillComponentWithText(Labeled labeled, String nameInBundle) {
        try {
            labeled.setText(ResourceBundle.getBundle("languages", LanguageSelection.getInstance().getLocale()).getString(nameInBundle));
            log.debug("The " + labeled.getClass().getSimpleName() + " with the name \"" + nameInBundle + "\" is filled with \"" + labeled.getText() + "\"");
        } catch (MissingResourceException e) {
            labeled.setText(nameInBundle);
            log.error("Can not find \"" + nameInBundle + "\" in ResourceBundle");
        }
    }
}
