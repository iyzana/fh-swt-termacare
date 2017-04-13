package de.adesso.termacare.database.construct;

import de.adesso.termacare.entity.EntityInterface;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static de.adesso.termacare.TerMa.logger;

public abstract class AbstractRepo<T extends EntityInterface, ID extends Serializable> implements Repo<T, ID>{

	private Class<T> clazz;
	private static SessionFactory factory;

	public AbstractRepo(Class<T> clazz){
		factory = new Configuration().configure().buildSessionFactory();
		this.clazz = clazz;
	}

	@Override
	public List<T> list(){
		Transaction tx = null;
		List<T> list = new LinkedList<>();
		try(Session session = factory.openSession()){
			tx = session.beginTransaction();
			Table t = clazz.getAnnotation(Table.class);
			List<T> qr = session.createQuery("FROM " + t.name(), clazz).list();
			list.addAll(qr);
			tx.commit();
		} catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void update(ID id, T instance){
		Transaction tx = null;
		try(Session session = factory.openSession()){
			tx = session.beginTransaction();
			T configurations = session.get(clazz, id);

			LinkedList<Field> fields = new LinkedList<>();
			Collections.addAll(fields, configurations.getClass().getDeclaredFields());

			for(Field field : fields){
				Type type = field.getType();
				try{
					if(type.getTypeName().equals(Boolean.class.getTypeName())){
						Method is = configurations.getClass().getDeclaredMethod("is" + field.getName(), type.getClass());
						Method set = configurations.getClass().getDeclaredMethod("set" + field.getName(), type.getClass());
						set.invoke(configurations, is.invoke(instance));
					} else{
						Method is = configurations.getClass().getDeclaredMethod("get" + field.getName(), type.getClass());
						Method set = configurations.getClass().getDeclaredMethod("set" + field.getName(), type.getClass());
						set.invoke(configurations, is.invoke(instance));
					}
				} catch(NoSuchMethodException e){
					logger.info("For field:" + field.getName() + " is no setter or getter defined", e);
				}
			}

			session.update(configurations);
			tx.commit();
		} catch(HibernateException e){
			if(tx != null) tx.rollback();
			logger.error("Failed to update the instance of " + clazz.getSimpleName() + " with ID:" + id, e);
		} catch(IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}

	@Override
	public ID add(T instance){
		Transaction tx = null;
		ID id = null;
		try(Session session = factory.openSession()){
			try{
				tx = session.beginTransaction();
				id = (ID)session.save(instance);
				tx.commit();
			} catch(HibernateException e){
				if(tx != null) tx.rollback();
				logger.error("Failed to add an instance of " + clazz.getSimpleName() + " with ID:" + id, e);
			}
		}
		return id;
	}

	@Override
	public T getByID(ID id){
		Session session = factory.openSession();
		T t = session.get(clazz, id);
		session.close();
		return t;
	}

	@Override
	public void delete(ID id){
		Transaction tx = null;
		logger.debug("Try to delete the entity with ID:" + id);
		try(Session session = factory.openSession()){
			tx = session.beginTransaction();
			T t = session.get(clazz, id);
			session.delete(t);
			tx.commit();
			logger.info(clazz.getSimpleName() + " with ID:" + id + " deleted:\n" + t.toString());
		} catch(HibernateException e){
			if(tx != null) tx.rollback();
			logger.error("Failed to delete the " + clazz.getSimpleName() + " with ID:" + id, e);
		}
	}
}
