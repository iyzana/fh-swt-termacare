package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.PatientEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PatientEditController extends AbstractController<PatientEdit>{

	@Setter
	private DAOPatient patient;

	private PatientService service;

	@Override
	public void init(Stage stage, Scene scene){
		init(new PatientEdit(this, stage, scene));
	}

	public void save(){
		service.createPatient(view.getTitleField().getText(), Gender.MALE, view.getGivenNameField().getText(),
		                      view.getFamilyNameField().getText(), generateBillingAddress(), generateLivingAddress()
		);
		backToOverview();
	}

	private Address generateLivingAddress(){
		return createAddress(
						view.getBillingPostcodeField().getText(), view.getBillingDepartureField().getText(),
						view.getBillingStreetField().getText(), view.getBillingNumberField().getText()
		);
	}

	private Address generateBillingAddress(){
		return createAddress(
						view.getLivingPostcodeField().getText(), view.getLivingDepartureField().getText(),
						view.getLivingStreetField().getText(), view.getLivingNumberField().getText()
		);
	}

	private Address createAddress(String postcode, String departure, String street, String number){
		Address address = new Address();
		address.setPostcode(postcode);
		address.setDeparture(departure);
		address.setAddress(street);
		address.setNumber(number);
		return address;
	}

	public void backToOverview(){
		OverviewController oc = DependencyInjector.getInstance(OverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}

}
