package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.data.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

import static de.adesso.termacare.TerMa.logger;

public class PatientRepo extends AbstractRepo<Patient, Integer>{

	public PatientRepo(){
		super(Patient.class);
	}
}
