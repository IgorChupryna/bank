package ua.chup.account;

public class NotMoreThanSummException extends Exception{
    public String getMessage() {
        return "Account total summ less than Transaction summ"+super.getMessage();
    }
}
