package de.adesso.termacare.database.construct;

import de.adesso.termacare.data.entity.EntityInterface;

import java.io.Serializable;
import java.util.List;

interface Repo<T extends EntityInterface, ID extends Serializable> {

	/**
	 * Returns all entities saved in the database
	 *
	 * @return A list of all entities
	 */
	List<T> list();

	/**
	 * Updates the instance with the given id with the new instance
	 *
	 * @param id       The id of the entity in the database
	 * @param instance The instance with the new values
	 */
	void update(ID id, T instance);

	/**
	 * Method to add an instance of T in the database
	 *
	 * @param instance The instance to add
	 * @return The ID of the created entity
	 */
	ID add(T instance);

	/**
	 * Method to get an instance with the given id
	 *
	 * @param id The id of the entity in the database
	 * @return The instance with the id
	 */
	T getByID(ID id);

	/**
	 * Method to DELETE an employee from the records
	 *
	 * @param id The id of the entity in the database
	 */
	void delete(ID id);
}
