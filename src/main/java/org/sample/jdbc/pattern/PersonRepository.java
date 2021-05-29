package org.sample.jdbc.pattern;

import org.sample.domain.entities.Person;

public interface PersonRepository {

    public void create(Person person);
    public Person read(int id);
    public void update(Person person);
    public void delete(Person person);
}
