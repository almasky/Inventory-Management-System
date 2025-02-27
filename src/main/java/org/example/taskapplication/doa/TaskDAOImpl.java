package org.example.taskapplication.doa;

import org.example.taskapplication.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void createTask(Task task) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error creating task: " + e.getMessage());
        }
    }

    @Override
    public Task getTaskById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Task.class, id);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Task", Task.class).list();
        }
    }

    @Override
    public void updateTask(Task task) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Task task) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void DueDateTask(Task task) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }
}
