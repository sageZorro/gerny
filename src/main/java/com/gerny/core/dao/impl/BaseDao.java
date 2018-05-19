package com.gerny.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gerny.core.dao.IBaseDao;
import com.gerny.core.vo.PageResults;

@SuppressWarnings(value={"unchecked", "rawtypes"})
public class BaseDao<T,ID extends Serializable>   implements IBaseDao<T,ID> {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;  
    protected Class<T> entityClass;  
  
    
	protected Class getEntityClass() {  
        if (entityClass == null) {  
            entityClass = (Class<T>) ((ParameterizedType) getClass()  
                    .getGenericSuperclass()).getActualTypeArguments()[0];  
        }  
        return entityClass;  
    }


	 /**  
     * <保存实体> <完整保存实体>  
     *   
     * @param t  
     *            实体参数  
     * @see com.itv.launcher.util.IBaseDao#save(java.lang.Object)  
     */  
    @Override  
    @Transactional
    public void save(T t) {  
        entityManager.persist(t);
    }  
  
  
    /**  
     * <get> <查找的get方法>  
     *   
     * @param id  
     *            实体的id  
     * @return 查询出来的实体  
     * @see com.itv.launcher.util.IBaseDao#get(java.io.Serializable)  
     */  
    @Override  
    public T get(ID id) {  
        T load = (T) entityManager.find(getEntityClass(), id);
        return load;  
    }  
  
    /**  
     * <contains>  
     *   
     * @param t  
     *            实体  
     * @return 是否包含  
     * @see com.itv.launcher.util.IBaseDao#contains(java.lang.Object)  
     */  
    @Override  
    public boolean contains(T t) {  
        return entityManager.contains(t);  
    }  
  
    /**  
     * <delete> <删除表中的t数据>  
     *   
     * @param t  
     *            实体  
     * @see com.itv.launcher.util.IBaseDao#delete(java.lang.Object)  
     */  
    @Override
    @Transactional
    public void delete(T t) {  
    	entityManager.remove(t);
    }  
  
    /**  
     * <根据ID删除数据>  
     *   
     * @param Id  
     *            实体id  
     * @return 是否删除成功  
     * @see com.itv.launcher.util.IBaseDao#deleteById(java.io.Serializable)  
     */  
    @Override
    @Transactional
    public boolean deleteById(ID Id) {  
        T t = get(Id);  
        if (t == null) {  
            return false;  
        }  
        delete(t);  
        return true;  
    }  
  
    /**  
     * <删除所有>  
     *   
     * @param entities  
     *            实体的Collection集合  
     * @see com.itv.launcher.util.IBaseDao#deleteAll(java.util.Collection)  
     */  
    @Override
    @Transactional
    public void deleteAll(Collection<T> entities) {  
        for (Object entity : entities) {  
        	entityManager.remove(entity);  
        }  
    }  
  
    /**  
     * <执行Hql语句>  
     *   
     * @param hqlString  
     *            hql  
     * @param values  
     *            不定参数数组  
     * @see com.itv.launcher.util.IBaseDao#queryHql(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public void queryHql(String hqlString, Object... values) {  
        Query query = entityManager.createQuery(hqlString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        query.executeUpdate();  
    }  
  
    /**  
     * <执行Sql语句>  
     *   
     * @param sqlString  
     *            sql  
     * @param values  
     *            不定参数数组  
     * @see com.itv.launcher.util.IBaseDao#querySql(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public void querySql(String sqlString, Object... values) {  
        Query query = this.entityManager.createNamedQuery(sqlString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        query.executeUpdate();  
    }  
  
    /**  
     * <根据HQL语句查找唯一实体>  
     *   
     * @param hqlString  
     *            HQL语句  
     * @param values  
     *            不定参数的Object数组  
     * @return 查询实体  
     * @see com.itv.launcher.util.IBaseDao#getByHQL(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public T getByHQL(String hqlString, Object... values) {  
        Query query = this.entityManager.createQuery(hqlString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        return (T) query.getSingleResult();
    }  
  
    /**  
     * <根据SQL语句查找唯一实体>  
     *   
     * @param sqlString  
     *            SQL语句  
     * @param values  
     *            不定参数的Object数组  
     * @return 查询实体  
     * @see com.itv.launcher.util.IBaseDao#getBySQL(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public T getBySQL(String sqlString, Object... values) {  
       
		Query query = this.entityManager.createNativeQuery(sqlString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        return (T) query.getSingleResult();
    }  
  
    /**  
     * <根据HQL语句，得到对应的list>  
     *   
     * @param hqlString  
     *            HQL语句  
     * @param values  
     *            不定参数的Object数组  
     * @return 查询多个实体的List集合  
     * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public List<T> getListByHQL(String hqlString, Object... values) {  
        Query query = this.entityManager.createQuery(hqlString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        return query.getResultList();
    }  
  
    /**  
     * <根据SQL语句，得到对应的list>  
     *   
     * @param sqlString  
     *            HQL语句  
     * @param values  
     *            不定参数的Object数组  
     * @return 查询多个实体的List集合  
     * @see com.itv.launcher.util.IBaseDao#getListBySQL(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public List<T> getListBySQL(String sqlString, Object... values) {  
        Query query = this.entityManager.createNamedQuery(sqlString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        return query.getResultList();
    }  
  
    /**  
     * 由sql语句得到List  
     *   
     * @param sql  
     * @param map  
     * @param values  
     * @return List  
     * @see com.itv.launcher.util.IBaseDao#findListBySql(java.lang.String,  
     *      com.itv.launcher.util.RowMapper, java.lang.Object[])  
     */  
    //TODO
    @Override  
    public List findListBySql(final String sql, Class clazz, 
            final Object... values) {  
        Query query =  entityManager.createNativeQuery(sql, clazz);
        if (values != null) {  
	        for (int i = 0; i < values.length; i++) {  
	            query.setParameter(i, values[i]);  
	        }  
        }  
        return query.getResultList();  
    }  
  
    /**  
     * <refresh>  
     *   
     * @param t  
     *            实体  
     * @see com.itv.launcher.util.IBaseDao#refresh(java.lang.Object)  
     */  
    @Override
    public void refresh(T t) {  
        this.entityManager.refresh(t);  
    }  
  
    /**  
     * <update>  
     *   
     * @param t  
     *            实体  
     * @see com.itv.launcher.util.IBaseDao#update(java.lang.Object)  
     */  
    @Override
    @Transactional
    public void update(T t) {  
        this.entityManager.merge(t);
    }  
  
    /**  
     * <根据HQL得到记录数>  
     *   
     * @param hql  
     *            HQL语句  
     * @param values  
     *            不定参数的Object数组  
     * @return 记录总数  
     * @see com.itv.launcher.util.IBaseDao#countByHql(java.lang.String,  
     *      java.lang.Object[])  
     */  
    @Override  
    public Long countByHql(String hql, Object... values) {  
        Query query = this.entityManager.createQuery(hql);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                query.setParameter(i, values[i]);  
            }  
        }  
        return (Long) query.getSingleResult();  
    }  
	@Override
	public PageResults<T> findPageByFetchedHql(String hql, String countHql, int start, int length,
			Object... values) {
		 	PageResults<T> retValue = new PageResults<T>();  
	        Query query = entityManager.createQuery(hql); 
	        if (values != null) {  
	            for (int i = 0; i < values.length; i++) {  
	                query.setParameter(i, values[i]);  
	            }  
	        }  
            Long count = countByHql(countHql, values);  
            retValue.setRecordsTotal(count.intValue());
            retValue.setRecordsFiltered(count.intValue());
	        List<T> itemList = query.setFirstResult(start).setMaxResults(length).getResultList();
	        if (itemList == null) {  
	            itemList = new ArrayList<T>();  
	        }  
	        retValue.setData(itemList);
	        
	        return retValue;  
	
	}


	
}
