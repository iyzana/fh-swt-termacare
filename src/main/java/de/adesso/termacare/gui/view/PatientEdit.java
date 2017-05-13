package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.PatientEditController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientEdit extends AbstractView<PatientEditController> {

    private Label givenNameLabel = new Label("given name");
    private Label familyNameLabel = new Label("family name");
    private Label titleLabel = new Label("title");

    private Label billingAddressLabel = new Label("billing address");
    private Label livingAddressLabel = new Label("living address");

    private TextField givenNameField = new TextField();
    private TextField familyNameField = new TextField();
    private TextField titleField = new TextField();

    private TextField billingStreetField = new TextField();
    private TextField billingPostcodeField = new TextField();
    private TextField billingDepartureField = new TextField();
    private TextField billingNumberField = new TextField();
    private TextField livingStreetField = new TextField();
    private TextField livingPostcodeField = new TextField();
    private TextField livingDepartureField = new TextField();
    private TextField livingNumberField = new TextField();

    private Button save = new Button("save");
    private Button cancel = new Button("backToOverview");

    private HBox titleBox = new HBox();
    private HBox givenNameBox = new HBox();
    private HBox familyNameBox = new HBox();
    private HBox billingAddressBox = new HBox();
    private HBox livingAddressBox = new HBox();
    private VBox contentBox = new VBox();
    private BorderPane pain = new BorderPane();
    private HBox bottomBox = new HBox();

    public PatientEdit(PatientEditController controller, Stage stage, Scene scene) {
        super(controller, stage, scene);
    }

    @Override
    public void init() {
        titleBox.getChildren().addAll(titleLabel, titleField);
        givenNameBox.getChildren().addAll(givenNameLabel, givenNameField);
        familyNameBox.getChildren().addAll(familyNameLabel, familyNameField);

        billingAddressBox.getChildren()
                .addAll(billingAddressLabel, billingPostcodeField, billingDepartureField, billingStreetField,
                        billingNumberField
                );
        livingAddressBox.getChildren()
                .addAll(livingAddressLabel, livingPostcodeField, livingDepartureField, livingStreetField,
                        livingNumberField
                );
        contentBox.getChildren().addAll(titleBox, givenNameBox, familyNameBox, billingAddressBox, livingAddressBox);
        bottomBox.getChildren().addAll(save, cancel);

        pain.setCenter(contentBox);
        pain.setBottom(bottomBox);
        scene.setRoot(pain);
    }

    @Override
    public void addPicturesToButtons() {

    }

    @Override
    public void fillComponentsWithSelectedLanguage() {

    }

    @Override
    public void registerListener() {
        save.setOnMouseClicked(event -> controller.save());
        cancel.setOnMouseClicked(event -> controller.backToOverview());
    }

    @Override
    public void setStyleClasses() {
        givenNameLabel.setId("givenNameLabel");
        familyNameLabel.setId("familyNameLabel");
        titleLabel.setId("titleLabel");

        givenNameField.setId("givenNameField");
        familyNameField.setId("familyNameField");
        titleField.setId("titleField");

        billingAddressLabel.setId("billingAddressLabel");
        billingPostcodeField.setId("billingPostcodeField");
        billingDepartureField.setId("billingDepartureField");
        billingStreetField.setId("billingStreetField");
        billingNumberField.setId("billingNumberField");
    }

}
