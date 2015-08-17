package student.logic;


public  class DaoException extends Exception {
    public DaoException(String msq) {
        super(msq);
    }
   public DaoException(String msg,Throwable cause){

   }
    public DaoException(){};

    public DaoException(Throwable cause) {

    }
}
