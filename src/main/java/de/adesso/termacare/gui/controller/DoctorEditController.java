package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAODoctor;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.DoctorEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoctorEditController extends AbstractController<DoctorEdit> {

    private DoctorService service;
    private long id;

    @Override
    public void init(Stage stage, Scene scene) {
        id = 0;
        init(new DoctorEdit(this, stage, scene));
    }

    public void save() {
        service.createOrUpdateDoctor(id, view.getTitleField().getText(), Gender.MALE, view.getGivenNameField().getText(),
                view.getFamilyNameField().getText()
        );
        backToOverview();
    }

    public void backToOverview() {
        DoctorOverviewController oc = DependencyInjector.getInstance(DoctorOverviewController.class);
        oc.init(stage, scene);
        oc.show();
    }

    public void setDoctor(DAODoctor doctor) {
        id = doctor.getId();
        view.getTitleField().setText(doctor.getTitle());
        view.getGivenNameField().setText(doctor.getGivenName());
        view.getFamilyNameField().setText(doctor.getFamilyName());
    }
}
