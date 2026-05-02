package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        // -------- INSERT MULTIPLE RECORDS --------
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Payment p1 = new Payment("Karthikeya", new Date(), "SUCCESS", 5000);
        Payment p2 = new Payment("Ravi", new Date(), "FAILED", 3000);
        Payment p3 = new Payment("sridevi", new Date(), "PENDING", 2000);

        session.save(p1);
        session.save(p2);
        session.save(p3);

        tx.commit();
        session.close();

        System.out.println("Records Inserted");

        // -------- DELETE USING HQL --------
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx2 = session2.beginTransaction();

        int deleteId = 2; // change ID as needed

        Query query = session2.createQuery("delete from Payment where id = :pid");
        query.setParameter("pid", deleteId);

        int rows = query.executeUpdate();

        tx2.commit();
        session2.close();

        System.out.println("Deleted Rows: " + rows);
    }
}