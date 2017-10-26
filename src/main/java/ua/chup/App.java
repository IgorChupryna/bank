package ua.chup;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.chup.account.*;
import ua.chup.currency.Currency;
import ua.chup.currency.CurrencyService;
import ua.chup.rate.JSON;
import ua.chup.rate.Rate;
import ua.chup.rate.RateService;
import ua.chup.responce.ResponceService;
import ua.chup.type.Type;
import ua.chup.type.TypeService;
import ua.chup.users.*;

import java.io.IOException;
import java.util.Scanner;


/*
mysql> select * from Currency;
+----+---------+----------+---------+
| id | type_in | type_out | value   |
+----+---------+----------+---------+
|  1 | UAH     | USD      |  0.0373 |
|  2 | USD     | UAH      |  26.805 |
|  3 | EUR     | USD      |  1.1723 |
|  4 | USD     | EUR      |  0.8529 |
|  5 | UAH     | EUR      |  0.0318 |
|  6 | EUR     | UAH      | 31.5608 |
|  7 | UAH     | UAH      |       1 |
|  8 | USD     | USD      |       1 |
|  9 | EUR     | EUR      |       1 |
+----+---------+----------+---------+

mysql> select * from Responce;
+----+-----------------------------------------------------+
| id | name                                                |
+----+-----------------------------------------------------+
|  1 | Success Transaction                                 |
|  2 | Not found credit account                            |
|  3 | Not found currency type                             |
|  4 | Summ less than zero                                 |
|  5 | Not found debit account                             |
|  6 | Many wrong parametrs of transaction                 |
|  7 | Summ transaction more than balance in debit account |
+----+-----------------------------------------------------+

mysql> select * from Type;
+----+----------------------------------+
| id | name                             |
+----+----------------------------------+
|  1 | Transfer from account to account |
|  2 | Replenishment                    |
|  3 | Conversion                       |
|  4 | Total balance                    |
+----+----------------------------------+

*/

public class App {
    private static UserService us = new UserService();
    private static AccountService as = new AccountService();
    private static CurrencyService cs = new CurrencyService();
    private static ResponceService rs = new ResponceService();
    private static TypeService ts = new TypeService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Press[1-5]:");
                System.out.println("1: Technical transactions");
                System.out.println("2: Replenishment account");
                System.out.println("3: Tranfer from account to account");
                System.out.println("4: Convert currency from user");
                System.out.println("5: Total summ from user");
                System.out.print("-> ");
                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        while (true) {
                            System.out.println("Press tech transaction:");
                            System.out.println("1: add User");
                            System.out.println("2: add Account");
                            System.out.println("3: add Currency");
                            System.out.println("4: add Type");
                            System.out.println("5: Rates from Yahoo Finance");
                            System.out.println("6: Delete user");
                            System.out.print("-> ");
                            s = sc.nextLine();
                            switch (s) {
                                case "1":
                                    addUser(sc);
                                    break;
                                case "2":
                                    addAccount(sc);
                                    break;
                                case "3":
                                    addCurrency(sc);
                                    break;
                                case "4":
                                    addType(sc);
                                    break;
                                case "5":
                                    refreshRates();
                                    break;
                                case "6":
                                    deleteUser(sc);
                                    break;
                                default:
                                    return;
                            }
                        }
                    case "2":
                        as.replenishment(sc);
                        break;
                    case "3":
                        as.transferOfFounds(sc);
                        break;
                    case "4":
                        as.convertCurr(sc);
                        break;
                    case "5":
                        as.summAccounts(sc);
                        break;
                    default:
                        return;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
            System.exit(0);
        }
    }

    private static void deleteUser(Scanner sc) {
        System.out.println("Id user");
        us.delete(sc.nextLine());
    }

    private static void refreshRates() throws IOException {
        String request = "https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance."
                + "xchange+where+pair+=+%22UAHUSD,USDUAH,EURUSD,USDEUR,UAHEUR,EURUAH%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        String result = RateService.performRequest(request);
        Gson gson = new GsonBuilder().create();
        JSON json = (JSON) gson.fromJson(result, JSON.class);
        Currency c = null;
        int i = 1;
        for (Rate rate : json.query.results.rate) {
            c = new Currency();
            c.setId(i);
            c.setTypeIn(rate.id.substring(0, 3));
            c.setTypeOut(rate.id.substring(3));
            c.setValue(rate.Rate);
            cs.update(c);
            i++;
        }

    }

    private static void addType(Scanner sc) {
        Type type = new Type();
        System.out.println("Type transaction:");
        type.setName(sc.nextLine());
        ts.persist(type);

    }

    private static void addCurrency(Scanner sc) {
        Currency c = new Currency();
        System.out.println("Currency IN:");
        c.setTypeIn(sc.nextLine());
        System.out.println("Currency OUT:");
        c.setTypeOut(sc.nextLine());
        System.out.println("Value:");
        c.setValue(sc.nextDouble());
        cs.persist(c);
    }

    private static void addUser(Scanner sc) {
        User u = new User();
        System.out.println("User name:");
        u.setName(sc.nextLine());
        us.persist(u);
    }

    private static void addAccount(Scanner sc) {
        Account a = new Account();
        System.out.println("Number:");
        a.setNumber(sc.nextLine());
        System.out.println("Type:");
        a.setType(sc.nextLine());
        System.out.println("User_id:");
        a.setUserId(us.findById(sc.nextLine()));
        System.out.println("Summ:");
        a.setSumm(sc.nextDouble());
        as.persist(a);
    }



}
