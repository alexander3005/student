package student.logic;
import java.sql.*;
import  java.util.*;
public  class StudentDao extends Dao<Student> {
   
    public StudentDao() throws DaoException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection==null) {
                connectionFactory = new ConnectionFactory();
                connection = connectionFactory.getConnection();
            }} catch (ClassNotFoundException e) {
            throw new DaoException(e);
        }
    }

    public PreparedStatement getInsert() throws DaoException {
        if(insert==null){
            try {
                insert= connection.prepareStatement( "insert into student (first_name,second_name) values (?,?);");
            } catch (SQLException e) {
                throw  new DaoException(e);
            }
        }
        return insert;
    }
   
    public PreparedStatement getSelect() throws DaoException {
        if(select==null){
            try {
                select=connection.prepareStatement("select id, first_name, second_name from student;");
            } catch (SQLException e) {
                throw  new DaoException(e);
            }
        }
        return select;
    }
    public PreparedStatement getUpdate() throws DaoException {
        if(update==null){
            try {
                update=connection.prepareStatement( "Update student set first_name=?,second_name=? where id=?;");
            } catch (SQLException e) {
                throw  new DaoException(e);
            }
        }
        return update;
    }
    public PreparedStatement getDelete() throws DaoException {
        if(delete==null){
            try {
                delete=connection.prepareStatement("delete from student where id=?;");
            } catch (SQLException e) {
                throw  new DaoException(e);
            }
        }
        return delete;
    }
    public void insert(Student obj) throws DaoException {
        try {
            getInsert();
            insert.setString(1, obj.getFirstName());
            insert.setString(2, obj.getSecondName());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw  new DaoException(e);
        }
    }
    
    public List<Student> select() throws DaoException {
        List<Student> students=new LinkedList<>();
        Student student=null;
        ResultSet resultSet=null;
        try {
            getSelect();
            resultSet=select.executeQuery();
            while(resultSet.next()){
                student=new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setSecondName(resultSet.getString("second_name"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw  new DaoException(e);
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw  new DaoException(e);
                }
            }
        }
        for(Student text:students)
            System.out.print(text);
        return students;
    }


    public void delete(int id) throws DaoException {
        try {
            
            getDelete();
            delete.setInt(1,id);
            delete.executeUpdate();
        } catch (SQLException e) {
            throw  new DaoException(e);
        }
    }

    public void update(Student obj) throws DaoException {
        try {
            getUpdate();


                update.setString(1, obj.getFirstName());
                update.setString(2, obj.getSecondName());
                update.setInt(3, obj.getId());
                update.executeUpdate();

        } catch (SQLException e) {
            throw  new DaoException(e);
        }

    }
  public Student getStudentById(int studentId)  throws DaoException{

        Student student=new Student();
        String query = "select * from student where student.id = " + studentId + " ";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        ResultSet res = null;
            res = stmt.executeQuery(query);
            if (res.next()) {
                student.setFirstName(res.getString("first_name"));
                student.setSecondName(res.getString("second_name"));
                student.setId(res.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return student;
    }

   

   


    public  void  closeConnection() throws DaoException {
        try{
            if (insert!=null ){
                insert.close();
            }
        }catch (SQLException e){
            throw  new DaoException(e);
        }
        try{
            if (select!=null ){
                select.close();
            }
        }catch (SQLException e){
            throw  new DaoException(e);
        }
        try{
            if (update!=null ){
                update.close();
            }
        }catch (SQLException e){
            throw  new DaoException(e);
        }
        try{
            if (delete!=null ){
                delete.close();
            }
        }catch (SQLException e){
            throw  new DaoException(e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw  new DaoException(e);
        }
    }
}




