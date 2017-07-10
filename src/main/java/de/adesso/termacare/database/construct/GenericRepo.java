package de.adesso.termacare.database.construct;

import de.adesso.termacare.database.entity.Address;
import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.EntityInterface;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.entity.Patient;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic database repository used for listing, saving, loading and deleteing of any entity that implements the
 * marker interface EntityInterface
 *
 * @param <T> Type of class to use this repository for
 * @param <ID> type of id the entity has
 */
@Slf4j
public class GenericRepo<T extends EntityInterface, ID extends Serializable> implements Repo<T, ID> {
    
    private Class<T> clazz;
    private static SessionFactory factory = createFactory();
    
    private static SessionFactory createFactory() {
        return new Configuration()
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(Medication.class)
                .setProperty("hibernate.dialect.storage_engine", "innodb")
                .buildSessionFactory();
    }
    
    public GenericRepo(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    /**
     * Returns all entities saved in the table
     *
     * @return A list of all entities
     */
    @Override
    public List<T> list() {
        Transaction tx = null;
        
        try (Session session = factory.openSession()) {
            List<T> list = new LinkedList<>();
            
            tx = session.beginTransaction();
            list.addAll(session.createQuery("FROM " + clazz.getSimpleName(), clazz).list());
            tx.commit();
            
            return list;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
    
    /**
     * Method to save an instance of T in the table
     *
     * @param instance The instance to save
     */
    @Override
    public void save(T instance) {
        try (Session session = factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(instance);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                log.error("Failed to save an instance of " + clazz.getSimpleName(), e);
                
                throw e;
            }
        }
    }
    
    /**
     * Method to get an instance with the given id
     *
     * @param id The id of the entity in the database
     * @return The instance with the id
     */
    @Override
    public T getByID(ID id) {
        Session session = factory.openSession();
        T t = session.get(clazz, id);
        session.close();
        return t;
    }
    
    /**
     * Method to DELETE an employee from the records
     *
     * @param id The id of the entity in the database
     */
    @Override
    public void delete(ID id) {
        Transaction tx = null;
        log.debug("Try to delete the entity with ID:" + id);
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            T t = session.get(clazz, id);
            session.delete(t);
            tx.commit();
            log.info(clazz.getSimpleName() + " with ID:" + id + " deleted:\n" + t.toString());
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Failed to delete the " + clazz.getSimpleName() + " with ID:" + id, e);
        }
    }
}
