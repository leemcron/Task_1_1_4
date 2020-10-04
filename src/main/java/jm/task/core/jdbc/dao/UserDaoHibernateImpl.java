package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Session session;


    public UserDaoHibernateImpl() {
        session = Util.getSessionFactory().openSession();
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users" +
                " (Id INT PRIMARY KEY AUTO_INCREMENT," +
                " Name varchar(30)," +
                " LastName varchar(35)," +
                " Age INT )";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        System.out.println("Database has been created!");
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE users";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        System.out.println("User с именем –  " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        transaction.commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sql = "DELETE FROM users";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
    }
}
