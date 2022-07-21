package db;

public class DBException extends Exception{
    public DBException (String message, Throwable cause) {
        System.err.println("Message: " + message + "; Cause: " + cause);
    }
}
