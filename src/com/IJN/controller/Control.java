package com.IJN.controller;

import com.IJN.dao.GoodDAO;
import com.IJN.dao.UserDAO;
import com.IJN.pojo.Good;
import com.IJN.pojo.User;
import com.IJN.util.RandomCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Control {

    public static void welcome() {
        while (true){
            System.out.println("===========WELCOME==========");
            System.out.println(">>Welcome to Online Shop!!<<");
            System.out.println("       Version 0.1.0        ");
            System.out.println("============================");
            System.out.println(" ");
            System.out.println("Press 0 -------------- login");
            System.out.println("Press 1 ----------- register");
            System.out.println("Press 2 --------------- exit");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            LOOP1: while (true){
                System.out.println("Input your choice: ");
                System.out.print(">>");
                int num = scan.nextInt();
                switch (num) {
                    case 0:
                        login();
                        break LOOP1;
                    case 1:
                        register();
                        break LOOP1;
                    case 2:
                        return;
                    default:
                        System.out.println("Invalid input! please choose again");
                }
            }
        }
    }

    public static void register() {
        System.out.println("============REGISTER===========");
        Scanner sc = new Scanner(System.in);
        String nameIn = "";
        String passwdIn = "";
        while (true){
            System.out.println("Input your Username: ");
            System.out.print(">>");
            nameIn = sc.nextLine();
            if(new UserDAO().get(nameIn)!=null){
                System.out.println("This username has been used");
                continue;
            }
            break;
        }
        System.out.println("Input your password: ");
        System.out.print(">>");
        passwdIn = sc.nextLine();
        User user = new User(nameIn,passwdIn);
        new UserDAO().addUser(user);

    }

    public static void login() {
        System.out.println("===============LOGIN===============");
        Scanner sc = new Scanner(System.in);
        String nameIn = "";
        String pwdIn = "";
        User user = null;
        //username
        while (true){
            System.out.println("Input your Username: ");
            System.out.print(">>");
            nameIn = sc.nextLine();
            user = new UserDAO().get(nameIn);
            if(user!=null){
                break;
            }
            System.out.println();
            System.out.println("  No account using this Username  ");
            System.out.println("==================================");
            System.out.println("Press 0 -------------- input again");
            System.out.println("Press 1 ------- create new account");
            System.out.println("Press 2 --------------------- exit");
            System.out.println();
            LOOP2: while (true){
                System.out.println("Input your choice: ");
                System.out.print(">>");
                int num = sc.nextInt();
                switch (num) {
                    case 0:
                        break LOOP2;
                    case 1:
                        register();
                        break LOOP2;
                    case 2:
                        return;
                    default:
                        System.out.println("Invalid input! please choose again");
                }
            }
        }
        //passwd
        while (true){
            System.out.println("Input your Password: ");
            System.out.print(">>");
            pwdIn = sc.nextLine();
            if(pwdIn.equals(user.password)){
                break;
            }
            System.out.println("password incorrect!");
            System.out.println("please input again");
            System.out.println();
        }
        //code
        while (true){
            RandomCode rand = new RandomCode();
            String code = rand.randCode();

            System.out.println();
            System.out.println("code: " + code);        //打印验证码字符串
            System.out.println("Input your code: ");
            System.out.print(">>");
            Scanner codeStrIn = new Scanner(System.in);     //输入字符串
            String codeIn = codeStrIn.nextLine();
            if(code.equalsIgnoreCase(codeIn)){          //无视大小写比对字符串
                break;
            }
            System.out.println("Failed. Retry pls ");
        }

        //success
        System.out.println("Success!");
        System.out.println("Going to your homepage...");
        try {
            new Thread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //goto homepage
        userHomepage(user);

    }

    public static void charge(User user){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.println("Input the amount");
            System.out.print(">>");
            double amount = sc.nextDouble();
            if(amount<=user.creditCardBalance){
                user.balance += amount;
                user.creditCardBalance -=amount;
                new UserDAO().update(user);
                System.out.println("Success!");
                System.out.println(user);
                System.out.println("Press any key to continue");
                sc.next();
                break;
            }
            System.out.println("Not enough money in your credit card");
        }
    }

    public static void changePassword(User user){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your new password: (Press 0 to return)");
        System.out.print(">>");
        String pwdIn = sc.nextLine();
        if(pwdIn.equals("0"))
            return;
        user.password = pwdIn;
        new UserDAO().update(user);
        System.out.println("Success!");
        System.out.println("Press any key to continue");
        sc.next();
    }

    //homepage
    public static void userHomepage(User user){
        while (true){
            System.out.println();
            System.out.println("========WELCOME TO YOUR HOMEPAGE========");
            System.out.println("Press 0 -------------------- online shop");
            System.out.println("Press 1 ------------------ check balance");
            System.out.println("Press 2 ------------------------- charge");
            System.out.println("Press 3 ---------------- change Password");
            System.out.println("Press 4 -------------------- Q&A Service");
            System.out.println("Press 5 ----------------------- feedback");
            System.out.println("Press 6 --------------------------- exit");

            Scanner sc = new Scanner(System.in);
            LOOP3: while (true){
                System.out.println("Input your choice: ");
                System.out.print(">>");
                int num = sc.nextInt();
                switch (num) {
                    case 0:
                        shopPage(user);
                        user = new UserDAO().get(user.username);
                        break LOOP3;
                    case 1:
                        System.out.printf("Your balance: %f \n",user.balance);
                        System.out.println("Press any key to continue");
                        sc.next();
                        break LOOP3;
                    case 2:
                        charge(user);
                        user = new UserDAO().get(user.username);
                        break LOOP3;
                    case 3:
                        changePassword(user);
                        user = new UserDAO().get(user.username);
                        break LOOP3;
                    case 4:
                        System.out.println("this module is not available for now");
                        break LOOP3;
                    case 5:
                        System.out.println("this module is not available for now");
                        break LOOP3;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid input! please choose again");
                }
            }
        }

    }

    public static void shopPage(User user){

        System.out.println();
        System.out.println("==========Online Shop==========");
        List<Good> goods = new GoodDAO().listAvailable();
        System.out.println(goods);
        System.out.println();

        Scanner sc = new Scanner(System.in);
        Good good = null;
        int amount = 0;

        while (true){
            System.out.println();
            System.out.println("Input the name of the good you want:");
            System.out.println("(Press 0 to abort purchase)");
            System.out.print(">>");
            String gname = sc.nextLine();
            if(gname.equals("0"))
                return;
            good = new GoodDAO().get(gname);
            if(good != null)
                break;
            System.out.println("No such good");
        }
        OuterLoop: while (true){
            InnerLoop: while (true){
                System.out.println("Input the amount of goods you want to purchase: ");
                System.out.println("(Press 0 to abort purchase)");
                System.out.print(">>");
                amount = sc.nextInt();
                if(amount==0)
                    return;
                if(amount<=good.quantity)
                    break InnerLoop;
                System.out.println("not enough goods in inventory");
            }
            if(amount*good.price<user.balance){
                break OuterLoop;
            }
            System.out.println("not enough money on your account");
        }
        good.quantity-=amount;
        user.balance-=amount*good.price;
        new GoodDAO().update(good);
        new UserDAO().update(user);

        System.out.println("Success!");
    }



    public static void main(String[] args) {
        welcome();



    }


}
