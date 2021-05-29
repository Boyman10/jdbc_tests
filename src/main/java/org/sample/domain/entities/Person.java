package org.sample.domain.entities;

public class Person {

    public int id;
    public String name;
    public String address;
    public String phone;
    public String email;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
