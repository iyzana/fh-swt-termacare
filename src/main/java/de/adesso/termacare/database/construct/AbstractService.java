package de.adesso.termacare.database.construct;

import de.adesso.termacare.entity.EntityInterface;

import java.io.Serializable;

public abstract class AbstractService<R extends Repo<E, S>, E extends EntityInterface, S extends Serializable>{}
