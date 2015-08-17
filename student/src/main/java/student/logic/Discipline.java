package student.logic;

public class Discipline  {
    int id;
    String nameDiscipline;
    public void setId(int id){
        this.id=id;
    }
    public void setNameDiscipline(String nameDiscipline){
        this.nameDiscipline=nameDiscipline;

    }
    public int getId(){
        return id;
    }
    public String getNameDiscipline(){
        return  nameDiscipline;
    }
    public String toString(){
        return " id:  " + id +" name_discipline:  "+ nameDiscipline+"\n";
    }


}