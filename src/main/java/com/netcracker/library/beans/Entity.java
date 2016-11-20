package com.netcracker.library.beans;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 14.11.16.
 */
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private GregorianCalendar lastUpdate;

    public Entity() {}

    public Entity(Entity entity) {
        this.id = entity.getId();
        this.lastUpdate = entity.getLastUpdate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(GregorianCalendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (id != entity.id) return false;
        return lastUpdate != null ? lastUpdate.equals(entity.lastUpdate) : entity.lastUpdate == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
