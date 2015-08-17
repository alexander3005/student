
package student.web;
import student.logic.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ServiceConfigurationError;


public class DisciplineController extends HttpServlet {
    private static String DisciplineList="/DisciplineList.jsp";
   private static String InsertOrEdit="/Discipline.jsp";
    private static String Back="/Main.jsp";
    private DisciplineDao disciplineDao;
    public DisciplineController(){
        super();
        try {
            disciplineDao=new DisciplineDao();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        String forward = "";
        String action=request.getParameter("action");
        try{
        if(action.equalsIgnoreCase("delete")){
            int disciplineId= Integer.parseInt(request.getParameter("disciplineId"));
            disciplineDao.delete(disciplineId);
            forward=DisciplineList;
            request.setAttribute("disciplines",disciplineDao.select());

        }
        else  if(action.equalsIgnoreCase("disciplineList")){
            forward=DisciplineList;
            request.setAttribute("disciplines",disciplineDao.select());
        }
        else if (action.equalsIgnoreCase("edit")) {
            forward = InsertOrEdit;
            int disciplineId = Integer.parseInt(request.getParameter("disciplineId"));

            Discipline discipline = disciplineDao.getDisciplineById(disciplineId);
            request.setAttribute("discipline", discipline);
        }
        else if(action.equalsIgnoreCase("back")){
            forward=Back;
        }
        else {
            forward = InsertOrEdit;
        }
        RequestDispatcher view=request.getRequestDispatcher(forward);
        view.forward(request,response);

    } catch (DaoException e) {
        e.printStackTrace();
    }}

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
       Discipline discipline=new Discipline();
        try {
           discipline.setNameDiscipline(request.getParameter("nameDiscipline"));
          ;
            String disciplineId = request.getParameter("disciplineId");
            if (disciplineId == null || disciplineId.isEmpty()) {
                disciplineDao.insert(discipline);
            }
            else
            {
               discipline.setId(Integer.parseInt(disciplineId));
                disciplineDao.update(discipline);
            }
            response.sendRedirect(request.getContextPath() + "/DisciplineController?action=disciplinelist");
        } catch (DaoException e) {
            e.printStackTrace();
        }}
    }