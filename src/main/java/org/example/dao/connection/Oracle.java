package org.example.dao.connection;

import org.apache.log4j.Logger;
import org.example.tools.custom.exceptions.ConnectionFailedException;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Hashtable;

public class Oracle implements ConnectionInterface{

    protected Connection connection = null;
    protected PreparedStatement statement = null;
    protected ResultSet result = null;
    private DataSource dataSource;
    private Context context;
    private Hashtable<String, String> hashtable = new Hashtable<>();
    private static final Logger logger = Logger.getLogger(Oracle.class);

    @Override
    public void connect() {
        try {
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup("MyData");
            connection = dataSource.getConnection();
        } catch (SQLException | NamingException e) {
            //throw new ConnectionFailedException("desc ", e);
            return;
        }
    }

    @Override
    public void disconnect() {
        try {
            if(connection!=null){
                connection.close();
            }
            if(context!=null){
                context.close();
            }
        } catch (SQLException | NamingException e) {
            //throw new ConnectionFailedException("desc ", e);
            return;
        }
    }

    @Override
    public void init() {
        //get data from initDB.sql
    }
}
