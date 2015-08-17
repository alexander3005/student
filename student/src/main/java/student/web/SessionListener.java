
package student.web;


import student.logic.DaoException;
import student.logic.DisciplineDao;
import student.logic.Student;
import student.logic.StudentDao;


import javax.servlet.http.*;

 public class SessionListener implements HttpSessionListener{
       private  StudentDao studentDao;
       private DisciplineDao disciplineDao;
       private  int sessionCount=0;
    public void sessionCreated(HttpSessionEvent event) {
        sessionCount++;
        try {
            studentDao=new StudentDao();
            disciplineDao=new DisciplineDao();
            HttpSession session = event.getSession();
            session.setAttribute("studentDao",studentDao);
            session.setAttribute("disciplineDao",disciplineDao);
            System.out.println("Session Created : " +session);
            System.out.println("Session Count : " + sessionCount);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    public void sessionDestroyed(HttpSessionEvent event) {
        sessionCount--;
        HttpSession session = event.getSession();
       try{
     studentDao.closeConnection();
     disciplineDao.closeConnection();
     }catch(DaoException e){
e.printStackTrace();
}
        System.out.println("Session Destroyed: " + session);
        System.out.println("Total Sessions: " + sessionCount);
}

}