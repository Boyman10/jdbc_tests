package org.sample.jdbc.pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.sample.domain.entities.Person;

import java.io.File;

public class HibernatePersonPersister implements PersonRepository {

    protected Session session = null;
    protected SessionFactory sessionFactory = null;

    /** Creates a registry from the configuration file, uses that to build a session factory, and then creates a session to use to communicate with the database */
    public HibernatePersonPersister() {

        File configFile = new File("hibernate.cfg.xml");

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(configFile)
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        session = sessionFactory.openSession();

    }

    /** Implements the 'Create' method of the CRUD interface. For hibernate this is the same as the update, so delegates to that
     */
    @Override
    public void create(Person aCustomer) {
        update(aCustomer);
    }

    /** Implements the 'Read' method of the CRUD interface,
     * returning the database entry that has the given ID */
    @Override
    public Person read(int id) {
        session.beginTransaction();
        Person customer = session.find(Person.class,  id);
        session.getTransaction().commit();
        return customer;
    }

    /** Implements the 'Update' method of the CRUD interface,
     * copying (or inserting) the given customer into the
     * database */
    @Override
    public void update(Person aCustomer) {
        session.beginTransaction();
        session.save( aCustomer ) ;
        session.getTransaction().commit();
    }

    /** Implements the 'Delete' method of the CRUD interface,
     * removing the given customer from the database */
    @Override
    public void delete(Person aCustomer) {
        session.beginTransaction();
        session.delete( aCustomer ) ;
        session.getTransaction().commit();
    }

}
