package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        insertDepartment(factory);

        deleteDepartmentById(factory, 1);

        factory.close();
    }

    public static void insertDepartment(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = new Department("IT", "New York", "John Doe");

        session.save(department);

        transaction.commit();
        session.close();
        System.out.println("Department inserted successfully.");
    }

    public static void deleteDepartmentById(SessionFactory factory, int departmentId) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Department WHERE id = ?1";

        Query query = session.createQuery(hql);
        query.setParameter(1, departmentId);

        int result = query.executeUpdate();

        transaction.commit();
        session.close();

        if (result > 0) {
            System.out.println("Department with ID " + departmentId + " deleted successfully.");
        } else {
            System.out.println("No Department found with ID " + departmentId);
        }
    }
}
