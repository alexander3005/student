package student.logic;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public  abstract class Dao<T>  {
    Connection connection;

    protected PreparedStatement insert;
    protected PreparedStatement select;
   protected PreparedStatement delete;
    protected PreparedStatement update;

    ConnectionFactory connectionFactory;
    public abstract  void insert(T obj) throws DaoException;
    public  abstract List<T> select() throws DaoException;
    public    abstract void delete (int id) throws DaoException;
    public abstract void update(T obj) throws DaoException;
    public abstract  void closeConnection() throws DaoException;

    public abstract PreparedStatement getInsert() throws DaoException ;
    public abstract PreparedStatement getDelete() throws DaoException;
    public abstract PreparedStatement getSelect() throws DaoException;
    public abstract PreparedStatement getUpdate() throws  DaoException;



}

