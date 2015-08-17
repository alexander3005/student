package student.logic;
public class Student {
    int id;
    String firstName;
    String secondName;

    public int getId(){
        return id;
    }
    public String getFirstName(){

        return firstName;
    }
    public String getSecondName(){

        return secondName;
    }
    public void setId(int id){

        this.id=id;
    }
    public void setFirstName(String firstName){

        this.firstName=firstName;
    }
    public void setSecondName(String secondName){
        this.secondName=secondName;
    }

    public String toString(){
        return "Id: "+ id +" First_Name: " + firstName+" Second_name: "+ secondName+"\n";
    }
}
