import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DataProvider {
    private SessionFactory sessionFactory;

    public DataProvider() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public List<Product> getAllProductsBoughtByCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> result = session.createNamedQuery("Customer.getBoughtProducts")
                .setParameter("customer", customer).getResultList();
        session.getTransaction().commit();
        return result;
    }

    public List<Customer> getAllCustomersBoughtThisProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> result = session.createNamedQuery("Product.getBoughtCustomers")
                .setParameter("product", product).getResultList();
        session.getTransaction().commit();
        return result;
    }

    public void deleteCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNamedQuery("Customer.delete").setParameter("customer", customer).executeUpdate();
        session.getTransaction().commit();
    }

    public void deleteProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNamedQuery("Product.delete").setParameter("product", product).executeUpdate();
        session.getTransaction().commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
