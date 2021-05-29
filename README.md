JDBC tests
----------

Featuring various dbs such as :

- Sqlite
- postgres
- MariaDb
- MongoDb
- Cassandra
- H2
...
  
Testing ORM such as Hibernate, manual JDBC, OpenJPA, EclipseLink...
  

# Sqlite

Can run in memory or using file storage.

https://github.com/xerial/sqlite-jdbc

Directly load the DB from the code using the proper library which includes the server.


# Hibernate

We use both Annotation and Config files (hbm.xml) to map our classes to tables.

## ehcache

Disable caching for proper debugging.

https://howtodoinjava.com/hibernate/hibernate-ehcache-configuration-tutorial/

## HSQL

Hibernate's query language (HSQL), which is similar to SQL except that it refers to the objects in the code rather than the tables in the database


# Patterns

## Facade pattern

See the Persister - it s an example of the facade pattern.
Ex : RepositoryPattern

1. An interface definition that declares the persistence methods. This is usually a set of CRUD calls.
2. A set of facade classes that implement the interface and hide (encapsulate) the complexity of persistence behind simple CRUD calls. 
3. A factory method (or injection) to select the appropriate persister for the application.
