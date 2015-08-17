package student.logic;

import java.io.*;
import java.sql.*;
import java.util.*;


public class ConnectionFactory {
    public Connection getConnection () throws DaoException {
        BufferedReader bufferedReader = null;
        Properties properties = new Properties();
        Connection connection = null;

        try {

            bufferedReader = new BufferedReader(new FileReader("D:\\Properties.txt"));
            properties.load(bufferedReader);
            connection = (Connection) DriverManager.getConnection(properties.getProperty("URL"),
                    properties.getProperty("USERNAME"), properties.getProperty("PASSWORD"));
        }catch (SQLException | IOException e ){
            throw new  DaoException();

        }


        return connection;
    }

}

