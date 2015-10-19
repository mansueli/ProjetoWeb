
package edu.utfpr.projetoweb.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;



public class Repository<E> {
    
    final EntityManager em = Persistence.createEntityManagerFactory("web").createEntityManager();
    
    private final Class<E> entityClass;
    
    public Repository(Class<E> entityClass){
        this.entityClass = entityClass; 
    }
    
    public E find(long id){
        return em.find(entityClass, id);
    }
    
    public List<E> findAll(){
        String query = String.format(
                  "from %s ",
                  entityClass.getSimpleName());
        return em.createQuery(query, entityClass).getResultList();
    }
    public void save(E entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.getTransaction().commit();
        
    }

    public void delete(E entity){
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
