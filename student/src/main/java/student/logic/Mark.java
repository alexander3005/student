package student.logic;
public class Mark {
   int id;
    int studentId;
    int disciplineId;
    int mark;
    public void setId(int id){
        this.id=id;
    }
    public void setStudentId(int studentId){
        this.studentId=studentId;

    }
    public void setDisciplineId (int disciplineId){
        this.disciplineId=disciplineId;
    }
            public void setMark(int mark){
                this.mark=mark;

            }
    public int getId(){
        return id;}
    public int getStudentId(){
        return studentId;}
    public int getDisciplineId(){
        return disciplineId;}
    public int getMark(){
        return  mark;
    }
    public  String toString(){
        return   "id: "+id +"student_id: "+ studentId+"discipline_id: "+disciplineId+"mark: "+mark+"\n";
    }
}