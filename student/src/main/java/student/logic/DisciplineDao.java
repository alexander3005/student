package student.logic;



import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public  class DisciplineDao extends Dao<Discipline>{
    public DisciplineDao() throws DaoException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection==null){
                connectionFactory=new ConnectionFactory();
                connection= connectionFactory.getConnection();
            }
        } catch (ClassNotFoundException e) {
           throw new DaoException();
        }
    }

    public PreparedStatement getInsert() throws  DaoException{
        if(insert==null){
            try {
                insert=connection.prepareStatement( "insert into discipline (name_discipline) values (?);");
            } catch (SQLException e){
                throw new DaoException();
            }
        }
        return insert;
    }
    public PreparedStatement getSelect() throws  DaoException{
        if(select==null){
            try {
                select=connection.prepareStatement("select id,name_discipline from discipline;");
            } catch (SQLException e) {
                throw new DaoException();
            }
        }
        return select;
    }
    public PreparedStatement getUpdate() throws DaoException{
        if(update==null){
            try {
                update=connection.prepareStatement("Update discipline set name_discipline=? where id=?;");
            } catch (SQLException e) {
               throw new DaoException();
            }
        }
        return update;
    }
    public PreparedStatement getDelete() throws DaoException{
        if(delete==null){
            try {
                delete=connection.prepareStatement( "delete from discipline where id=?;");
            } catch (SQLException e) {
               throw  new DaoException();
            }
        }
        return delete;
    }




    public void insert(Discipline obj) throws DaoException {
        try {
            getInsert();
            insert.setString(1, obj.getNameDiscipline());
            insert.executeUpdate();
        } catch (SQLException e) {
          throw  new DaoException();
        }
    }

    public List<Discipline> select() throws DaoException {
        List<Discipline> disciplines =new LinkedList<>();
        Discipline discipline=null;
        ResultSet resultSet=null;
        try {
            getSelect();
            resultSet=select.executeQuery();
            while(resultSet.next()){
                discipline=new Discipline();
                discipline.setId(resultSet.getInt("id"));
                discipline.setNameDiscipline(resultSet.getString("name_discipline"));
                disciplines.add(discipline);
            }
        } catch (SQLException e) {
          throw  new DaoException();
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
        }
        for(Discipline text:disciplines)
        System.out.print(text);
        return disciplines;
    }

    @Override
    public void delete( int id) throws DaoException {
        try {
            getDelete();
            delete.setInt(1, id);
            delete.executeUpdate();

        } catch (SQLException e) {
           throw new DaoException();
        }
    }

    
  public Discipline getDisciplineById(int disciplineId)  throws DaoException{

       Discipline discipline=new Discipline();
        String query = "select * from discipline where discipline.id = " + disciplineId + " ";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet res = null;
            res = stmt.executeQuery(query);
            if (res.next()) {

                discipline.setNameDiscipline(res.getString("name_discipline"));
              discipline.setId(res.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return discipline;
    }
    public void update(Discipline obj) throws DaoException{
      try{
          getUpdate();
            update.setString(1, obj.getNameDiscipline());
            update.setInt(2, obj.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public void closeConnection()  throws DaoException{

        try{
            if (insert!=null ){
                insert.close();
            }
        }catch (SQLException e){
           throw new DaoException();
        }
        try{
            if (select!=null ){
                select.close();
            }
        }catch (SQLException e){
           throw new DaoException();
        }
        try{
            if (update!=null ){
                update.close();
            }
        }catch (SQLException e){
         throw new DaoException();
        }
        try{
            if (delete!=null ){
                delete.close();
            }
        }catch (SQLException e){
          throw new DaoException();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
          throw  new DaoException();
        }

    }
}