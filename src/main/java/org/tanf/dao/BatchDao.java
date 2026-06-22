package org.tanf.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tanf.entities.Batch;
import org.tanf.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class BatchDao {
//    •	Create Batch
//•	Update Batch
//•	Search Batch
//•	View All Batches
public void saveBatch(Batch batch){
    Transaction transcation=null;
    try(Session session= HibernateUtil.getSessionFactory().openSession()){
        transcation=session.beginTransaction();
        session.persist(batch);
        transcation.commit();
    } catch (Exception e) {
        if(transcation!=null){
            transcation.rollback();
        }
        e.printStackTrace();
    }
}
    public Optional<Batch> getBatchById(Long id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return Optional.ofNullable(session.get(Batch.class,id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBatch(Batch batch){
        Transaction transaction=null;
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            session.merge(batch);
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
    public  Optional<List<Batch>> getAllBatches() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return Optional.ofNullable(session.createQuery(
                    "FROM Batch",
                    Batch.class
            ).getResultList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteBatch(Long id){
        Transaction transaction=null;
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            transaction =session.beginTransaction();
            Batch user=session.get(Batch.class,id);
            if(user!=null){
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
