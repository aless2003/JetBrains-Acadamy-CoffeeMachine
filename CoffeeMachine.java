package machine;
import java.io.IOException;
import java.util.Scanner;


public class CoffeeMachine {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean retry = false, exit = false, coffeePossible = true;
        String choice = "", back = "0";
        int chCoffee, water = 400, milk = 540, beans = 120, cups = 9, savedMoney = 550, price = 0,waterConsume= 0, milkConsume = 0, beansConsume = 0;
        do {
            do {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                choice = sc.next();
                retry = !choice.equals("buy") && !choice.equals("fill") && !choice.equals("take") && !choice.equals("remaining");
                if (choice.equals("exit")) {
                    exit = true;
                }
            } while (retry && !exit);
            ExecuteChoose executeChoose = new ExecuteChoose(sc, choice, water, milk, beans, cups, savedMoney, price, waterConsume, milkConsume, beansConsume).invoke();
            water = executeChoose.getWater();
            milk = executeChoose.getMilk();
            beans = executeChoose.getBeans();
            cups = executeChoose.getCups();
            savedMoney = executeChoose.getSavedMoney();
            price = executeChoose.getPrice();
            waterConsume = executeChoose.getWaterConsume();
            milkConsume = executeChoose.getMilkConsume();
            beansConsume = executeChoose.getBeansConsume();

        } while (!exit);
    }

    private static class Buy {
        private Scanner sc;
        private int water;
        private int milk;
        private int beans;
        private int cups;
        private int savedMoney;
        private int price;
        private int waterConsume;
        private int milkConsume;
        private int beansConsume;

        public Buy(Scanner sc, int water, int milk, int beans, int cups, int savedMoney, int price, int waterConsume, int milkConsume, int beansConsume) {
            this.sc = sc;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
            this.savedMoney = savedMoney;
            this.price = price;
            this.waterConsume = waterConsume;
            this.milkConsume = milkConsume;
            this.beansConsume = beansConsume;
        }

        public int getWater() {
            return water;
        }

        public int getMilk() {
            return milk;
        }

        public int getBeans() {
            return beans;
        }

        public int getCups() {
            return cups;
        }

        public int getSavedMoney() {
            return savedMoney;
        }

        public int getPrice() {
            return price;
        }

        public int getWaterConsume() {
            return waterConsume;
        }

        public int getMilkConsume() {
            return milkConsume;
        }

        public int getBeansConsume() {
            return beansConsume;
        }

        public Buy invoke() {
            String back;
            int chCoffee;
            boolean retry;
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
            back = sc.next();
            if (!back.equals("back")) {
                chCoffee = Integer.parseInt(back);
                switch (chCoffee) {
                    case 1:
                        waterConsume = 250;
                        milkConsume = 0;
                        System.out.println("");
                        beansConsume = 16;
                        price = 4;
                        retry = false;
                        break;
                    case 2:
                        waterConsume = 350;
                        beansConsume = 20;
                        milkConsume = 75;
                        price = 7;
                        retry = false;
                        break;
                    case 3:
                        waterConsume = 200;
                        beansConsume = 12;
                        milkConsume = 100;
                        price = 6;
                        retry = false;
                        break;
                }

                if (water - waterConsume >= 0) {
                    if (beans - beansConsume >= 0) {
                        if (milk - milkConsume >= 0) {
                            if (cups - 1 >= 0) {
                                water -= waterConsume;
                                beans -= beansConsume;
                                milk -= milkConsume;
                                cups--;
                                savedMoney += price;
                                System.out.println("I have enough resources, making you a coffee!");
                            } else {
                                System.out.println("Sorry, not enough cups!");
                            }
                        } else {
                            System.out.println("Sorry, not enough milk!");
                        }
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                } else {
                    System.out.println("Sorry, not enough water!");
                }
            }
            return this;
        }
    }

    private static class Fill {
        private Scanner sc;
        private int water;
        private int milk;
        private int beans;
        private int cups;

        public Fill(Scanner sc, int water, int milk, int beans, int cups) {
            this.sc = sc;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
        }

        public int getWater() {
            return water;
        }

        public int getMilk() {
            return milk;
        }

        public int getBeans() {
            return beans;
        }

        public int getCups() {
            return cups;
        }

        public Fill invoke() {
            System.out.println("Write how many ml of water you add:");
            water = water + sc.nextInt();
            System.out.println("Write how many ml of milk you add:");
            milk = milk + sc.nextInt();
            System.out.println("Write how many grams of coffee beans you add:");
            beans = beans + sc.nextInt();
            System.out.println("How man disposable cups you add:");
            cups = cups + sc.nextInt();
            return this;
        }
    }

    private static class ExecuteChoose {
        private Scanner sc;
        private String choice;
        private int water;
        private int milk;
        private int beans;
        private int cups;
        private int savedMoney;
        private int price;
        private int waterConsume;
        private int milkConsume;
        private int beansConsume;

        public ExecuteChoose(Scanner sc, String choice, int water, int milk, int beans, int cups, int savedMoney, int price, int waterConsume, int milkConsume, int beansConsume) {
            this.sc = sc;
            this.choice = choice;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
            this.savedMoney = savedMoney;
            this.price = price;
            this.waterConsume = waterConsume;
            this.milkConsume = milkConsume;
            this.beansConsume = beansConsume;
        }

        private static int take(int savedMoney, String s, int i) {
            System.out.println(s);
            savedMoney = i;
            return savedMoney;
        }

        private static void printState(int water, int milk, int beans, int cups, int savedMoney) {
            System.out.println("The coffee machine has:");
            System.out.println(water + " of water");
            System.out.println(milk + " of milk");
            System.out.println(beans + " of coffee beans");
            System.out.println(cups + " of disposable cups");
            System.out.println(savedMoney + " of money\n");
        }

        public int getWater() {
            return water;
        }

        public int getMilk() {
            return milk;
        }

        public int getBeans() {
            return beans;
        }

        public int getCups() {
            return cups;
        }

        public int getSavedMoney() {
            return savedMoney;
        }

        public int getPrice() {
            return price;
        }

        public int getWaterConsume() {
            return waterConsume;
        }

        public int getMilkConsume() {
            return milkConsume;
        }

        public int getBeansConsume() {
            return beansConsume;
        }

        public ExecuteChoose invoke() {
            switch (choice) {
                case "buy":
                    Buy buy = new Buy(sc, water, milk, beans, cups, savedMoney, price, waterConsume, milkConsume, beansConsume).invoke();
                    water = buy.getWater();
                    milk = buy.getMilk();
                    beans = buy.getBeans();
                    cups = buy.getCups();
                    savedMoney = buy.getSavedMoney();
                    price = buy.getPrice();
                    waterConsume = buy.getWaterConsume();
                    milkConsume = buy.getMilkConsume();
                    beansConsume = buy.getBeansConsume();
                    break;
                case "fill":
                    Fill fill = new Fill(sc, water, milk, beans, cups).invoke();
                    water = fill.getWater();
                    milk = fill.getMilk();
                    beans = fill.getBeans();
                    cups = fill.getCups();
                    break;
                case "take":
                    savedMoney = take(savedMoney, "I gave you $" + savedMoney, 0);
                    break;
                case "remaining":
                    printState(water, milk, beans, cups, savedMoney);
                    break;
            }
            return this;
        }
    }
}