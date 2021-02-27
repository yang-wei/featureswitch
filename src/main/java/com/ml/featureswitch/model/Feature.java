package com.ml.featureswitch.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Feature {

    private String name;
    private @Id @GeneratedValue Long id;

    public Feature() {}
    public Feature(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable
    Set<User> users;

    public boolean hasUser(User user) {
        return users.contains(user);
    }

    public void addUser(User user) {
        if (!hasUser(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        if (hasUser(user)) {
            users.remove(user);
        }
    }
}
