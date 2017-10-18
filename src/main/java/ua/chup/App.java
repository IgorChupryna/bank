package ua.chup;


import ua.chup.account.*;
import ua.chup.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static UserService us = new UserService();
    private static AccountService as = new AccountService();


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {

            while (true) {
                System.out.println("Press[1-5]:");
                System.out.println("1: add User");
                System.out.println("2: add Account");
                System.out.println("3: Costs from-to");
                System.out.println("4: Only discount");
                System.out.println("5: Random group with summ weight 1000g.");
                System.out.print("-> ");
                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        addUser(sc);
                        break;
                    case "2":
                        addAccount(sc);
                        break;
                    case "3":
                        //addAccount(sc);
                        break;
                    case "4":
                        /*onlyDiscount();
                        break;
                    case "5":
                        randomGroupOneKg();
                        break;*/
                    default:
                        return;
                }
            }

        } finally {
            sc.close();
            System.exit(0);
        }
    }

    private static void addUser(Scanner sc){
        User u = new User();
        System.out.println("User name:");
        u.setName(sc.nextLine());
        us.persist(u);
    }
    private static void addAccount(Scanner sc){
        Account a = new Account();
        System.out.println("Number:");
        a.setNumber(sc.nextLine());
        System.out.println("Type:");
        a.setType(sc.nextLine());
        System.out.println("User_id:");
        a.setUserId(sc.nextInt());
        System.out.println("Summ:");
        a.setSumm(sc.nextDouble());
        as.persist(a);
    }
 /*   private static void costsFromTo(Scanner sc){
        System.out.println("From price(double)(COMMA):");
        Double from = sc.nextDouble();
        System.out.println("To price(double)(COMMA):");
        Double to = sc.nextDouble();
        for (Menu m:costsFromTo(from,to)) {
                System.out.println(m);
        }
    }
    private static List<Menu> costsFromTo(Double from, Double to){
        List<Menu> res = new ArrayList<>();
        List<Menu> listTemp = ms.findAll();
        for (Menu m:listTemp) {
            if(m.getPrice() > from & m.getPrice() < to)
                res.add(m);
        }
        return res;
    }

    private static void onlyDiscount(){
        List<Menu> listTemp = ms.findAll();
        for (Menu m:listTemp) {
            if(m.getDiscount()==true)
                System.out.println(m);
        }
    }


    private static void randomGroupOneKg(){
        Integer to = 1000;
        List<Menu> listTemp=archGroup(ms.findAll(),null,to);
        List<Menu> listRes=new ArrayList<>();
        int rnd = 0;
        Menu techMenu=null;
        boolean check=false;
        while(true) {
            check=false;
            rnd = (int)(Math.random() * (listTemp.size()));
            if(rnd==listTemp.size())rnd=rnd-1;
            techMenu=listTemp.get(rnd);
            listRes.add(techMenu);
            to-=techMenu.getWeight();
            if(to==0)break;
            listTemp=archGroup(listTemp,techMenu,to);
            for(Menu l : listTemp){
                if(l.getWeight()<=to)
                    check=true;
            }
            if(!check)break;
        }

        for(Menu r:listRes) {
            System.out.println(r);
        }
    }
    private static List<Menu> archGroup(List<Menu> menus, Menu m, Integer to){
        List<Menu> listRes= new ArrayList<>();
        for(Menu i:menus) {
            if(i.getWeight()<=to)
                listRes.add(i);
        }
        if(m!=null)
            if (m.getWeight() <= to)
                listRes.remove(m);

        return listRes;
    }
*/

}
