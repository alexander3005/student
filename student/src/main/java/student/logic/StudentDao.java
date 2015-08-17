package student.logic;
import java.sql.*;
import  java.util.*;
public  class StudentDao extends Dao<Student> {
    PreparedStatement selectJournal;
    PreparedStatement insertJournal;
    public StudentDao() throws DaoException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection==null) {
                connectionFactory = new ConnectionFactory();
                connection = connectionFactory.getConnection();
            }} catch (ClassNotFoundException e) {
            throw new DaoException();
        }
    }

    public PreparedStatement getInsert() throws DaoException {
        if(insert==null){
            try {
                insert= connection.prepareStatement( "insert into student (first_name,second_name) values (?,?);");
            } catch (SQLException e) {
                throw  new DaoException();
            }
        }
        return insert;
    }
    public PreparedStatement getInsertJournal() throws DaoException {
        if(insertJournal==null){
            try {
                insertJournal= connection.prepareStatement( "insert into journal (student_id,discipline_id) values (?,?);");
            } catch (SQLException e) {
                throw  new DaoException();
            }
        }
        return insertJournal;
    }
    public PreparedStatement getSelect() throws DaoException {
        if(select==null){
            try {
                select=connection.prepareStatement("select id, first_name, second_name from student;");
            } catch (SQLException e) {
                throw  new DaoException();
            }
        }
        return select;
    }
    public PreparedStatement getUpdate() throws DaoException {
        if(update==null){
            try {
                update=connection.prepareStatement( "Update student set first_name=?,second_name=? where id=?;");
            } catch (SQLException e) {
                throw  new DaoException();
            }
        }
        return update;
    }
    public PreparedStatement getDelete() throws DaoException {
        if(delete==null){
            try {
                delete=connection.prepareStatement("delete from student where id=?;");
            } catch (SQLException e) {
                throw  new DaoException();
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
            throw  new DaoException();
        }
    }
    public void insertJournal (Journal obj) throws DaoException {
        try {
            getInsertJournal();

            insertJournal.setInt(1, obj.getStudentId());
            insertJournal.setInt(2,obj.getDisciplineId());

           insertJournal.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
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
            throw  new DaoException();
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw  new DaoException();
                }
            }
        }
        for(Student text:students)
            System.out.print(text);
        return students;
    }

    @Override
    public void delete( int id) throws DaoException {
        try {
            getDelete();
            delete.setInt(1,id);
            delete.executeUpdate();
        } catch (SQLException e) {
            throw  new DaoException();
        }
    }



    @Override
    public void update(Student obj) throws DaoException {
        try {
            getUpdate();


                update.setString(1, obj.getFirstName());
                update.setString(2, obj.getSecondName());
                update.setInt(3, obj.getId());
                update.executeUpdate();

        } catch (SQLException e) {
            throw  new DaoException();
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
            throw new DaoException();
        }
        return student;
    }

    public PreparedStatement getselectJournal() throws DaoException {
        if(selectJournal==null){
            try {
                selectJournal=connection.prepareStatement(" select j.id,s.second_name, s.first_name,d.name_discipline\n" +
                        "              from journal j inner join\n" +
                        "              student s on j.student_id=s.id\n" +
                        "              inner join\n" +
                        "              discipline d on j.discipline_id=d.id\n" +
                        "              ");
            } catch (SQLException e) {
                throw  new DaoException();
            }
        }
        return selectJournal;
    }

    public LinkedList<Table> join() throws DaoException {
        LinkedList<Table> joins=new LinkedList<>();
        ResultSet resultSet=null;
        try {
            getselectJournal();
            resultSet=selectJournal.executeQuery();
            while (resultSet.next()) {
                Discipline discipline=new Discipline();
                Student student=new Student();
                Journal journal=new Journal();
                Table join=new Table(discipline,journal,student);

                journal.setId(resultSet.getInt("id"));
                journal.setDisciplineId(resultSet.getInt("discipline_id"));
                journal.setStudentId(resultSet.getInt("student_id"));
                student.setSecondName(resultSet.getString("second_name"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setId(resultSet.getInt("id"));
                discipline.setId(resultSet.getInt("id"));
                discipline.setNameDiscipline(resultSet.getString("name_discipline"));
                joins.add(join);
            }
        } catch (SQLException e) {
            throw  new DaoException();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw  new DaoException();
                }
            }
        }
        for(Table text:joins){
            System.out.print(text);}
        return  joins;
    }


    public  void  closeConnection() throws DaoException {
        try{
            if (insert!=null ){
                insert.close();
            }
        }catch (SQLException e){
            throw  new DaoException();
        }
        try{
            if (select!=null ){
                select.close();
            }
        }catch (SQLException e){
            throw  new DaoException();
        }
        try{
            if (update!=null ){
                update.close();
            }
        }catch (SQLException e){
            throw  new DaoException();
        }
        try{
            if (delete!=null ){
                delete.close();
            }
        }catch (SQLException e){
            throw  new DaoException();
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




