package ua.chup.account;


import ua.chup.currency.CurrencyService;
import ua.chup.tranz.Tranzaction;
import ua.chup.tranz.TranzactionService;
import ua.chup.users.User;
import ua.chup.users.UserDao;

import java.util.*;

public class AccountService implements AccountServiceInterface {


    private static AccountDao accountDao;
    private static UserDao userDao;
    private static CurrencyService currencyService;
    private static TranzactionService tranzactionService;

    public AccountService() {
        accountDao = new AccountDao();
        currencyService = new CurrencyService();
        tranzactionService = new TranzactionService();
    }

    public void persist(Account entity) {
        accountDao.openCurrentSessionwithTransaction();
        accountDao.persist(entity);
        accountDao.closeCurrentSessionwithTransaction();
    }

    public void update(Account entity) {
        accountDao.openCurrentSessionwithTransaction();
        accountDao.update(entity);
        accountDao.closeCurrentSessionwithTransaction();
    }

    public Account findById(String id) {
        accountDao.openCurrentSession();
        Account account = accountDao.findById(id);
        accountDao.closeCurrentSession();
        return account;
    }

    public void delete(String id) {
        accountDao.openCurrentSessionwithTransaction();
        Account account = accountDao.findById(id);
        accountDao.delete(account);
        accountDao.closeCurrentSessionwithTransaction();
    }

    public List<Account> findAll() {
        accountDao.openCurrentSession();
        List<Account> account = accountDao.findAll();
        accountDao.closeCurrentSession();
        return account;
    }

    public void deleteAll() {
        accountDao.openCurrentSessionwithTransaction();
        accountDao.deleteAll();
        accountDao.closeCurrentSessionwithTransaction();
    }

    public AccountDao accountDao() {
        return accountDao;
    }

    @Override
    public void replenishment(Scanner sc) {
        Tranzaction tr = new Tranzaction(2, 0);
        System.out.println("Id account:");
        String acc = sc.nextLine();
        Account account = findById(acc);
        if (account == null) tr.setRespId(2);

        System.out.println("Currency transaction(UAH/USD/EUR):");
        String cur = sc.nextLine().toUpperCase();
        if (!cur.equals("USD") & !cur.equals("UAH") & !cur.equals("EUR")) if (tr.getRespId() == 0) tr.setRespId(3);
        else tr.setRespId(6);
        System.out.println("Summ transaction:");
        double summ = sc.nextDouble();
        if (summ <= 0.0) if (tr.getRespId() == 0) tr.setRespId(4);
        else tr.setRespId(6);
        Double value = 0.0;
        if (cur.equals(account.getType()))
            value = summ + account.getSumm();
        else
            value = account.getSumm() + summ * currencyService.getValueCurr(cur, account.getType()).getValue();

        account.setSumm(value);
        update(account);

        tr.setAccCredit(account.getNumber());
        tr.setValue(value);
        tr.setTypeCurAccCr(account.getType());
        tr.setTypeCurTrn(cur);
        tranzactionService.persist(tr);
    }

    @Override
    public void transferOfFounds(Scanner sc) throws NotSearchAccountException, NotMoreThanZeroException, NotSearchTypeCurrencyException, NotMoreThanSummException {
        Tranzaction tr = new Tranzaction(1, 0);
        Double val1 = null, val2 = null;

        System.out.println("Id account Debit:");
        String accDb = sc.nextLine();
        Account accountDb = findById(accDb);

        if (accountDb == null) throw new NotSearchAccountException();

        System.out.println("Id account Credit:");
        String accCr = sc.nextLine();
        Account accountCr = findById(accCr);

        if (accountCr == null) throw new NotSearchAccountException();

        System.out.println("Currency transaction(UAH/USD/EUR):");
        String cur = sc.nextLine().toUpperCase();

        Double debToCur = currencyService.getValueCurr(accountDb.getType(), cur).getValue();
        Double curToDeb = currencyService.getValueCurr(cur, accountDb.getType()).getValue();
        Double curToCre = currencyService.getValueCurr(cur, accountCr.getType()).getValue();
        if (!cur.equals("USD") & !cur.equals("UAH") & !cur.equals("EUR")) throw new NotSearchTypeCurrencyException();

        System.out.println("Summ transaction:");
        double summ = sc.nextDouble();

        if (summ <= 0.0) throw new NotMoreThanZeroException();
        if (summ > accountDb.getSumm() * debToCur) throw new NotMoreThanSummException();

        if (accountDb.getType().equals(cur)) {
            val1 = summ;
            accountDb.setSumm(accountDb.getSumm() - val1);
        } else {
            val1 = summ * curToDeb;
            Double d = accountDb.getSumm() - val1;
            accountDb.setSumm(d);
        }

        if (accountCr.getType().equals(cur))
            val2 = summ + accountCr.getSumm();
        else
            val2 = accountCr.getSumm() + val1 * curToCre;

        accountCr.setSumm(val2);
        if (tr.getRespId() == 0) {
            update(accountDb);
            update(accountCr);
        }

        tr.setAccDebit(accountDb.getNumber());
        tr.setAccCredit(accountCr.getNumber());
        tr.setValue(summ);
        tr.setTypeCurAccCr(accountCr.getType());
        tr.setTypeCurTrn(cur);
        tranzactionService.persist(tr);
    }

    @Override
    public void convertCurr(Scanner sc) throws NotSearchAccountException{
        Tranzaction tr = new Tranzaction(3, 0);
        Map<Integer,Account> accountsUser = new HashMap<>();
        System.out.println("Enter user id:");
        Integer userId = sc.nextInt();
        List<Account> accounts = findAll();
        String userName=null;
        System.out.println("Id           Number Type Summ");
        for (Account a : accounts) {
            if (userId == a.getUserId().getId()) {
                userName= a.getUserId().getName();
                accountsUser.put(a.getId(),a);
                System.out.println(a.getId() + "  " + a.getNumber() + " " + a.getType() + "  " + a.getSumm());
            }
        }

        System.out.println("Enter debit account user's "+userName);
        Account accountDb = accountsUser.get(sc.nextInt());
        if(accountDb==null) throw new NotSearchAccountException();

        System.out.println("Enter credit account user's "+userName);
        Account accountCr = accountsUser.get(sc.nextInt());
        if(accountCr==null) throw new NotSearchAccountException();

        Double valDb=null,valCr=null;
        valDb=accountDb.getSumm()*currencyService.getValueCurr(accountDb.getType(),accountCr.getType()).getValue();
        valCr=accountCr.getSumm()+valDb;

        accountDb.setSumm(0.0);
        accountCr.setSumm(valCr);

        tr.setAccDebit(accountDb.getNumber());
        tr.setAccCredit(accountCr.getNumber());
        tr.setTypeCurTrn(accountDb.getType());
        tr.setTypeCurAccCr(accountCr.getType());
        tr.setValue(accountDb.getSumm());

        update(accountDb);
        update(accountCr);
        tranzactionService.persist(tr);
    }

    @Override
    public void summAccounts(Scanner sc) {
        Tranzaction tr = new Tranzaction(4, 0);
        Map<Integer,Account> accountsUser = new HashMap<>();
        System.out.println("Enter user id:");
        Integer userId = sc.nextInt();
        List<Account> accounts = findAll();
        String userName=null;
        System.out.println("Id           Number Type Summ");
        String accs="";
        for (Account a : accounts) {
            if (userId == a.getUserId().getId()) {
                userName= a.getUserId().getName();
                accountsUser.put(a.getId(),a);
                System.out.println(a.getId() + "  " + a.getNumber() + " " + a.getType() + "  " + a.getSumm());
                accs+=a.getNumber()+",";
            }

        }
        accs=accs.substring(0,accs.length()-1);
        Double total=0.0;

        for (Account a:accounts) {
            total+=a.getSumm()*currencyService.getValueCurr(a.getType(),"UAH").getValue();
        }
        System.out.println(userName+"'s total summ in UAH["+accs+"]: "+total);

        tr.setTypeCurTrn("UAH");
        tr.setValue(total);
        tranzactionService.persist(tr);
    }


}
