package org.tanf.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory factory=buildSessionFactory();
    public static SessionFactory buildSessionFactory(){
        try{
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            System.out.println("Initial creation failed");
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory(){
        return factory;
    }
    public static void shutdown(){
        getSessionFactory();
    }
}