import org.hibernate.Session;

import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        DataProvider provider = new DataProvider();

        Session session = provider.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, 1);
        System.out.println(customer);
        Product product = session.get(Product.class, 1);
        System.out.println(product);
        session.getTransaction().commit();

        System.out.println(customer.getName() + " купил: " + provider.getAllProductsBoughtByCustomer(customer).stream()
                .map(Product::getName)
                .collect(Collectors.toList()));

        System.out.println(product.getName() + " был куплен: " + provider.getAllCustomersBoughtThisProduct(product).stream()
                .map(Customer::getName)
                .collect(Collectors.toList()));

        session = provider.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Customer customerForDeleting = new Customer();
        customerForDeleting.setName("new customer");
        session.save(customerForDeleting);
        session.getTransaction().commit();
        System.out.println("добавлен для последующего удаления " + customerForDeleting);

        provider.deleteCustomer(customerForDeleting);

        session = provider.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product productForDeleting = new Product();
        productForDeleting.setName("new product");
        productForDeleting.setCost(500);
        session.save(productForDeleting);
        session.getTransaction().commit();
        System.out.println("добавлен для последующего удаления " + productForDeleting);

        provider.deleteProduct(productForDeleting);
    }
}
