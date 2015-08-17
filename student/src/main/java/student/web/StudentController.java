package student.web;

import student.logic.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StudentController extends HttpServlet {
    private StudentDao studentDao;
    private static String InsertOrEdit= "/Student.jsp";
    private static String StudentList="/StudentList.jsp";
    private static String Back="/Main.jsp";
    public StudentController() {
        super();
        try {
            studentDao=new StudentDao();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        String forward = "";
        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("delete")) {
                int studentId = Integer.parseInt(request.getParameter("studentId"));
                studentDao.delete(studentId);
                forward=StudentList;
                request.setAttribute("students",studentDao.select());
            }
            else if (action.equalsIgnoreCase("edit")) {
                forward = InsertOrEdit;
                int studentId = Integer.parseInt(request.getParameter("studentId"));

                Student student = studentDao.getStudentById(studentId);
                request.setAttribute("student", student);
            }

            else if(action.equalsIgnoreCase("studentList")){
                forward=StudentList;
                request.setAttribute("students",studentDao.select());
            }else if(action.equalsIgnoreCase("back")){
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

    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        Student student=new Student();
        try {
            student.setFirstName(request.getParameter("FirstName"));
            student.setSecondName(request.getParameter("SecondName"));
            String studentId = request.getParameter("studentId");
            if (studentId == null || studentId.isEmpty()) {
                studentDao.insert(student);
            }
            else
            {
                student.setId(Integer.parseInt(studentId));
                studentDao.update(student);
            }
            response.sendRedirect(request.getContextPath() + "/StudentController?action=studentlist");
        } catch (DaoException e) {
            e.printStackTrace();
        }}

}