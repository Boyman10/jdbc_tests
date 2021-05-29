package org.sample.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.sample.domain.entities.Person;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class PersonHibernatePersister {

    protected SessionFactory sessionFactory = null;

    public PersonHibernatePersister() {

        ClassLoader classLoader = getClass().getClassLoader();
        File configFile = new File(Objects.requireNonNull(classLoader.getResource("hibernate.cfg.xml")).getFile());

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(configFile)
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    /** In hibernate 'creating' is the same as 'updating' */
    public void createCustomer(Person aCustomer) {
        updateCustomer(aCustomer);
    }

    /** Creates a session, a transaction for that session,
     * gives the customer instance to the  hibernate library
     * to prepare to store, and then commits the transaction
     * to persist it */
    public void updateCustomer(Person aCustomer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save( aCustomer ) ;
        session.getTransaction().commit();
        session.close();
    }

    /** Creates a session, a transaction for that session,
     * instructs hibernate to delete the entry, and then commits the transaction */
    public void deleteCustomer(Person aCustomer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete( aCustomer ) ;
        session.getTransaction().commit();
        session.close();
    }

    /** Creates a session, a transaction for that session,
     * instructs hibernate to find the entry with the given
     * unique identifier, and then commits the transaction to
     * run the query*/
    public Person readCustomer(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person customer = session.find(Person.class,  id);
        session.getTransaction().commit();
        session.close();
        return customer;
    }

    /** Creates a SQL-like query criteria to list all the
     * customers in the database. */
    public List<Person> listCustomers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Person> customers = (List<Person>) session.createQuery( "from Person" ).list();
        session.getTransaction().commit();
        session.close();
        return customers;
    }

    /** A quick and dirty test, and a demonstration of usage */
    public static void main(String[] args) throws IOException, SQLException {

        Person samjones = new Person();
        samjones.name = "Sam Jones";
        samjones.address = "12 Letsbe Avenue, Royston Vasey, MK2 3AU";
        samjones.email = "sam.jones@openclassrooms.co.uk";
        samjones.phone = "+44 7700 900081";

        PersonHibernatePersister persister = new PersonHibernatePersister();
        persister.updateCustomer(samjones);

        Person storedSam = persister.readCustomer(samjones.id);
        if (!storedSam.toString().equals(samjones.toString())) {
            throw new RuntimeException("Stored Sam "+storedSam+" is not equal to original Sam "+samjones);
        }
        else
        {
            System.out.println("Just did it !\n" + storedSam);
        }
    }
}

