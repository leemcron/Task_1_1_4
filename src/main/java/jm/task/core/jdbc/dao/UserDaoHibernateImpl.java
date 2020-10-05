package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Session session = Util.getSessionFactory().openSession();
    Transaction transaction;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            //session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    " (Id INT PRIMARY KEY AUTO_INCREMENT," +
                    " Name varchar(30)," +
                    " LastName varchar(35)," +
                    " Age INT )";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Database has been created!");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
           // session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String sql = "DROP TABLE users";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            //session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем –  " + name + " добавлен в базу данных");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
          //  session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
          //  session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error " + e.getMessage());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
         //   session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM users";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error " + e.getMessage());
        }
    }
}
