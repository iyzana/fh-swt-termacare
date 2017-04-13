package de.adesso.termacare.database.construct;

import de.adesso.termacare.entity.EntityInterface;

import java.io.Serializable;

public abstract class AbstractRepo<T extends EntityInterface, ID extends Serializable> implements Repo<T, ID> {}
