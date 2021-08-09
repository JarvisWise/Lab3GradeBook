package org.example.dao.connection;

import org.apache.log4j.Logger;

import java.sql.*;

public class Oracle implements ConnectionInterface{

    protected Connection connection = null;
    protected PreparedStatement statement = null;
    protected ResultSet result = null;
    private static final Logger logger = Logger.getLogger(Oracle.class);

    @Override
    public void connect() {
        //will be oracle conn
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "ama_user","1234");
            //return !connection.isClosed();
        } catch (SQLException | ClassNotFoundException throwables) {
            return;
        }
    }

    @Override
    public void disconnect() {
        //will be oracle conn
        try {
            if(connection!=null){
                connection.close();
            }
            return;
        } catch (SQLException throwables) {
            return;
        }
    }

    @Override
    public void init() {
        //get data from initDB.sql
    }
}
