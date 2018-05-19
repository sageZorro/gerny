package com.gerny.core.service;

import java.io.Serializable;
import java.util.Collection;

public interface IBaseService<T, ID extends Serializable> {
	 public abstract void save(T t);  
	    public abstract T get(ID id);  
	    public abstract void delete(T t);  
	    public abstract boolean deleteById(ID Id);  
	    public abstract void deleteAll(Collection<T> entities);
	    public abstract void update(T t);  
}
