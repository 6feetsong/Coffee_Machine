package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {

    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cup = 9;
    private static int money = 550;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        getAction();
    }

    private static void showStock() {
        System.out.println("");
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", beans);
        System.out.printf("%d of disposable cups\n", cup);
        System.out.printf("%d of money\n", money);
        getAction();
    }

    private static void getAction() {
        System.out.println("");
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();
//        System.out.println(action);
        switch (action) {
            case "buy":
                doBuy();
                break;
            case "fill":
                doFill();
                break;
            case "take":
                doTake();
                break;
            case "remaining":
                showStock();
                break;
            case "exit":
                break;
        }

    }

    private static void doBuy() {
        System.out.println("");
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        try {
            int menu = Integer.valueOf(scanner.next());
            int needWater = 0;
            int needBeans = 0;
            int needMilk = 0;
            int price = 0;
            switch (menu) {
                // the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
                case 1:
                    needWater = 250;
                    needBeans = 16;
                    price = 4;
                    break;
                // latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
                case 2:
                    needWater = 350;
                    needMilk = 75;
                    needBeans = 20;
                    price = 7;
                    break;
                // cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
                case 3:
                    needWater = 200;
                    needMilk = 100;
                    needBeans = 12;
                    price = 6;
                    break;
            }

            if (needWater != 0 && water < needWater) {
                System.out.println("Sorry, not enough water!");
                getAction();
                return;
            }

            if (needMilk != 0 && milk < needMilk) {
                System.out.println("Sorry, not enough milk!");
                getAction();
                return;
            }

            if (needBeans != 0 && beans < needBeans) {
                System.out.println("Sorry, not enough coffee beans!");
                getAction();
                return;
            }

            water -= needWater;
            milk -= needMilk;
            beans -= needBeans;
            cup--;
            money += price;
            System.out.println("I have enough resources, making you a coffee!");
            getAction();
        } catch (Exception e) {
            e.printStackTrace();
            getAction();
        }
    }

    private static void doFill() {
        System.out.println("");
        System.out.println("Write how many ml of water do you want to add:");
        int addWater = scanner.nextInt();
        water += addWater;
        System.out.println("Write how many ml of milk do you want to add:");
        int addMilk = scanner.nextInt();
        milk += addMilk;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addBeans = scanner.nextInt();
        beans += addBeans;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int addCup = scanner.nextInt();
        cup += addCup;

        getAction();
    }

    private static void doTake() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;

        getAction();
    }

    private static int getMaxCup(int water, int milk, int beans) {
        int maxWater = water / 200;
        int maxMilk = milk / 50;
        int maxBeans = beans / 15;

        int[] arr = new int[]{maxWater, maxMilk, maxBeans};
        Arrays.sort(arr);
        return arr[0];
    }
}
