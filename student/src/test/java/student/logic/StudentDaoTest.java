package student.logic;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
public class StudentDaoTest  {
    public void testSelect() throws Exception{
  
        StudentDao studentDao=new StudentDao();
        Student student = new Student();
        student.setFirstName("S");
        student.setSecondName("S");
        List< Student> list1=studentDao.select();
        assertNotNull(list1);
        assertTrue(list1.size()>0);


    }
   
    public void testInsert() throws Exception{
       
            StudentDao studentDao=new StudentDao();
            Student student = new Student();
            List<Student> list1 = studentDao.select();
            student.setFirstName("Sasha");
            student.setSecondName("L");
            studentDao.insert(student);
            List<Student> list2 = studentDao.select();
            assertTrue(list1.size()< list2.size() );
      
    }


 public void testUpdate() throws Exception{

        StudentDao studentDao=new StudentDao();
        Student student=new Student();
        int id=1;
        String firstName="Sasha";
        String secondName="Wasi";
        student.setId(id);
        student.setFirstName(firstName);
        student.setSecondName(secondName);
        studentDao.update(student);
        assertEquals(student.id,id);
        assertEquals(student.firstName,firstName);
        assertEquals(student.secondName,secondName);


    }
    public void testgetStudentById() throws Exception {
      StudentDao studentDao=new StudentDao();
        Student student=new Student();
        student.setId(1);
        student.setFirstName("Sasha");
        student.setSecondName("Lix");
        studentDao.update(student);
        studentDao.getStudentById(student.id);
        assertEquals(student.firstName,"Sasha");
        assertEquals(student.secondName,"Lix");

    }
    public void testDelete() throws Exception{

        StudentDao studentDao=new StudentDao();
        Student student=new Student();
        List<Student> studentListOne=studentDao.select();
        studentDao.delete(136);
        List<Student> studentListTwo=studentDao.select();
       assertTrue(studentListOne.size()>studentListTwo.size());



    }
   
   


}

