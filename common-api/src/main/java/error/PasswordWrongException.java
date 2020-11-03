package error;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(){
        super("password is wrong");
    }
}
