package ua.chup.account;

public class NotSearchAccountException extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage()+" Account not exists";
    }
}
