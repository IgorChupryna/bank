package ua.chup.account;

public class NotMoreThanZeroException extends RuntimeException {
    public String getMessage() {
        return "Account total summ more than zero"+super.getMessage();
    }
}
