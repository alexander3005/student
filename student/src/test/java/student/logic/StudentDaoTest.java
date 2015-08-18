package student.logic;
import junit.framework.TestCase;
import junit.framework.TestSuite;
        import java.util.List;
import static junit.framework.TestCase.assertTrue;
public class StudentDaoTest  {
    public void testInsert() throws Exception {

          DaoException daoException=new DaoException();
        StudentDao studentDao=new StudentDao();
        Student student=new Student();
        List<Student> list1=studentDao.select();
        student.setFirstName("Sasha");
        student.setSecondName("L");
        studentDao.insert(student);
        List<Student> list2=studentDao.select();
        assertTrue(list1.size()<list2.size());
    }

}


