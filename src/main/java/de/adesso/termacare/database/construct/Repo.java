package de.adesso.termacare.database.construct;

import de.adesso.termacare.entity.EntityInterface;

import java.io.Serializable;

interface Repo<T extends EntityInterface, ID extends Serializable> {}
