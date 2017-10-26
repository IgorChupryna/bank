package ua.chup.account;

import java.util.List;
import java.util.Scanner;

public interface AccountServiceInterface {
    public void  replenishment(Scanner sc);
    public void  transferOfFounds(Scanner sc) throws NotMoreThanSummException;
    public void  convertCurr(Scanner sc);
    public void  summAccounts(Scanner sc);

}