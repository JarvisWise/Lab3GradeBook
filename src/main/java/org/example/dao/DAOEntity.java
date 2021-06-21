package org.example.dao;

import org.example.entities.Entity;

import java.sql.ResultSet;

public interface DAOEntity {

    Entity parse(ResultSet result);
    Entity getEntityById();
    void addEntity(Entity entity);
}
