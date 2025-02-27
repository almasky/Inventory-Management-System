package org.example.taskapplication.doa;

import org.example.taskapplication.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        @Override
        public void createUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User created successfully " + user);
        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            System.out.println("Error creating user: " + e.getMessage());
            }
        }

    @Override
        public User getUserById(int id) {
            try (Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
       }
    }
        @Override
        public List<User> getAllUsers() {
             try (Session session = sessionFactory.openSession()) {
             return session.createQuery("from User", User.class).list();
        }
    }
        @Override
        public void updateUser (User user){
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.update(user);
                transaction.commit();
                System.out.println("User updated successfully " + user);
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
        @Override
        public void deleteUser(User user) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(user);
                transaction.commit();
                System.out.println("User deleted successfully " + user);
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }

    }

