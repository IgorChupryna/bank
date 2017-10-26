package ua.chup.account;

public class NotSearchTypeCurrencyException extends RuntimeException {
    @Override
    public String getMessage() {
        return "This type of currency not exists "+super.getMessage();
    }

    @Override
    public void printStackTrace() {
        System.out.println("This type of currency not exists ");
        super.printStackTrace();
    }
}
