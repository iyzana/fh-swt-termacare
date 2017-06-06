package de.adesso.termacare.gui.controller;

import de.adesso.termacare.database.entity.Address;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.gui.construct.AbstractEditController;
import de.adesso.termacare.gui.dto.DtoPatient;
import de.adesso.termacare.gui.view.PatientEdit;
import de.adesso.termacare.service.PatientService;
import de.adesso.termacare.util.DependencyInjector;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor
public class PatientEditController extends AbstractEditController<PatientEdit>{

	private long id;
	private PatientService service;

	@Override
	public void init(Stage stage, Scene scene){
		id = 0;
		init(new PatientEdit(this, stage, scene));
	}

	public void save(){
		service.createOrUpdatePatient(id, view.getTitleField().getText(), Gender.MALE, view.getGivenNameField().getText(),
		                      view.getFamilyNameField().getText(), generateBillingAddress(), generateLivingAddress()
		);
		backToOverview();
	}

	private Address generateBillingAddress(){
		return createAddress(
						view.getBillingPostcodeField().getText(), view.getBillingDepartureField().getText(),
						view.getBillingStreetField().getText(), view.getBillingNumberField().getText()
		);
	}

	private Address generateLivingAddress(){
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
		PatientOverviewController oc = DependencyInjector.getInstance(PatientOverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}

	public void setPatient(DtoPatient patient) {
		id = patient.getId();
		view.getTitleField().setText(patient.getTitle());
		view.getGivenNameField().setText(patient.getGivenName());
		view.getFamilyNameField().setText(patient.getFamilyName());

		setBillingAddress(patient);
		setLivingAddress(patient);
	}

	private void setBillingAddress(DtoPatient patient) {
		String[] billingPostcode = patient.getBillingPostcode().split(" ");
		if (billingPostcode.length >= 2) {
			String departure = IntStream.range(1, billingPostcode.length).mapToObj(i -> billingPostcode[i]).collect(Collectors.joining(" "));

			view.getBillingPostcodeField().setText(billingPostcode[0]);
			view.getBillingDepartureField().setText(departure);
		}

		String[] billingAddress = patient.getBillingAddress().split(" ");
		if (billingAddress.length >= 2) {
			String street = IntStream.range(0, billingAddress.length - 1).mapToObj(i -> billingAddress[i]).collect(Collectors.joining(" "));

			view.getBillingStreetField().setText(street);
			view.getBillingNumberField().setText(billingAddress[billingAddress.length - 1]);
		}
	}

	private void setLivingAddress(DtoPatient patient) {
		String[] livingPostcode = patient.getLivingPostcode().split(" ");
		if (livingPostcode.length >= 2) {
			String departure = IntStream.range(1, livingPostcode.length).mapToObj(i -> livingPostcode[i]).collect(Collectors.joining(" "));

			view.getLivingPostcodeField().setText(livingPostcode[0]);
			view.getLivingDepartureField().setText(departure);
		}

		String[] livingAddress = patient.getLivingAddress().split(" ");
		if (livingAddress.length >= 2) {
			String street = IntStream.range(0, livingAddress.length - 1).mapToObj(i -> livingAddress[i]).collect(Collectors.joining(" "));

			view.getLivingStreetField().setText(street);
			view.getLivingNumberField().setText(livingAddress[livingAddress.length - 1]);
		}
	}

	@Override
	public void setDisable(boolean disable){

	}
}
