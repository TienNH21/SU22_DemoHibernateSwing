package dao;

import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import utils.JpaUtils;

public class UserDAO {
    private EntityManager em;
    
    public UserDAO() {
        this.em = JpaUtils.getEntityManager();
    }
    
    public User create(User entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.flush();
            this.em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    
    public User update(User entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
            
            this.em.flush();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    
    public void delete(User entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.remove(entity);
            this.em.getTransaction().commit();
            
            this.em.flush();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    
    public List<User> all() {
        String jpql = "SELECT obj FROM User obj";
        TypedQuery<User> query = this.em.createQuery(jpql, User.class);
        
        return query.getResultList();
    }
    
    public User findById(int id) {
        return this.em.find(User.class, id);
    }
    
    public List<User> paginate(int page, int limit) {
        String jpql = "SELECT obj FROM User obj";
        TypedQuery<User> query = this.em.createQuery(jpql, User.class);
        
        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);

        return query.getResultList();
    }
    
    public User findByUsername(String username) throws NoResultException, NonUniqueResultException {
        String jpql = "SELECT obj FROM User obj WHERE username = :username";
        TypedQuery<User> query = this.em.createQuery(jpql, User.class);
        query.setParameter("username", username);
        
        try {
            User result = query.getSingleResult();
            return result;
            
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public long countAll() {
        String jpql = "SELECT COUNT(obj.id) FROM User obj";
        TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
        
        return query.getSingleResult();
    }
}
