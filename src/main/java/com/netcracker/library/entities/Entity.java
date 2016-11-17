package com.netcracker.library.entities;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 14.11.16.
 */
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int id;
    protected GregorianCalendar lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }

    public Entity() {}

    public Entity(int id) {
        this.id = id;
    }

    public Entity(int id, GregorianCalendar lastUpdate) {
        this.id = id;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
