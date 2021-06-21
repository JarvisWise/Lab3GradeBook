package org.example.dao;

import org.example.entities.Entity;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class DAOStudentSubjectEntity implements DAOEntity{
    @Override
    public Entity parse(ResultSet result) {
        return null;
    }

    @Override
    public Entity getEntityById() {
        return null;
    }

    @Override
    public void addEntity(Entity entity) {

    }
}
