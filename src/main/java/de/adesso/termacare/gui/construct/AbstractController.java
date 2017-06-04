package de.adesso.termacare.gui.construct;

import de.adesso.termacare.gui.language.LanguageSelection;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import static de.adesso.termacare.gui.language.LanguageSelection.getInstance;

@Slf4j
public abstract class AbstractController<T extends AbstractView> implements Controller {
    
    protected T view;
    protected Stage stage;
    protected Scene scene;
    
    protected void init(T view) {
        view.init();
        this.view = view;
        getInstance().setView(view);
        scene = view.getScene();
        stage = view.getStage();
        log.info("Initialised in: \"" + this.getClass().getSimpleName() + "\" the view: \"" + view.getClass().getSimpleName() + "\"");
    }
    
    @Override
    public void show() {
        view.show();
    }
    
    LanguageSelection getLanguageSelection() {
        return getInstance();
    }
}
