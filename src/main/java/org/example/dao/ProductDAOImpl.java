package org.example.dao;

import org.example.Dto.ProductDto;
import org.example.config.HibernateConfig;
import org.example.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;

    //depency injection is a technique where an object receives other objects that it depends on.
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).list();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(Long id) {
        {
            try (Session session = sessionFactory.openSession()) {
                return session.get(Product.class, id);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}