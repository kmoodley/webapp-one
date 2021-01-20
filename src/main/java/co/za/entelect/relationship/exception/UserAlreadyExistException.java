package co.za.entelect.relationship.exception;

public class UserAlreadyExistException extends Exception
{
    public UserAlreadyExistException(String message)
    {
        super(message);
    }
}
