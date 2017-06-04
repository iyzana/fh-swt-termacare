package de.adesso.termacare.database.construct;

import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.data.entity.EntityInterface;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.data.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static de.adesso.termacare.TerMa.logger;

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
                logger.error("Failed to save an instance of " + clazz.getSimpleName(), e);
                
                throw e;
            }
        }
    }
    
    @Override
    public T getByID(ID id) {
        Session session = factory.openSession();
        T t = session.get(clazz, id);
        session.close();
        return t;
    }
    
    @Override
    public void delete(ID id) {
        Transaction tx = null;
        logger.debug("Try to delete the entity with ID:" + id);
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            T t = session.get(clazz, id);
            session.delete(t);
            tx.commit();
            logger.info(clazz.getSimpleName() + " with ID:" + id + " deleted:\n" + t.toString());
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error("Failed to delete the " + clazz.getSimpleName() + " with ID:" + id, e);
        }
    }
}
