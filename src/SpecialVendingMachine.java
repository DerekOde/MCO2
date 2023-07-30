

import java.util.ArrayList;
import java.util.Scanner;

public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<Smoothie> customizableProducts;
    private ArrayList<Fruit> customizableIngredients;
    private Scanner scanner = new Scanner(System.in);

    public SpecialVendingMachine() {
        super();
        this.customizableProducts = new ArrayList<>();
        this.customizableIngredients = new ArrayList<>();
        customizableIngredients.add(new Fruit("Apple", 95, 1));
        customizableIngredients.add(new Fruit("Banana", 105, 1));
        customizableIngredients.add(new Fruit("Orange", 45, 1));
        customizableIngredients.add(new Fruit("Strawberry", 5, 1));
        customizableIngredients.add(new Fruit("Blueberry", 20, 1));
        customizableProducts.add(new Smoothie("Apple ", 95, 10));
        customizableProducts.add(new Smoothie("Orange" , 45, 15));
        customizableProducts.add(new Smoothie("Strawberry", 60, 20));
    }

    // Function to add a customizable product to the vending machine
    public void addCustomizableProduct(Smoothie product) {
        customizableProducts.add(product);
    }

    // Function to remove a customizable product from the vending machine
    public void removeCustomizableProduct(Smoothie product) {
        customizableProducts.remove(product);
    }

    // Function to add a customizable ingredient to the vending machine
    public void addCustomizableIngredient(Fruit ingredient) {
        customizableIngredients.add(ingredient);
    }

    // Function to remove a customizable ingredient from the vending machine
    public void removeCustomizableIngredient(Fruit ingredient) {
        customizableIngredients.remove(ingredient);
    }

    // Function to display all available customizable products
    public void displayCustomizableProducts() {
        System.out.println("Customizable Products:");
        for (int i = 0; i < customizableProducts.size(); i++) {
            Smoothie product = customizableProducts.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " (" + product.getCalories() + " Calories, Price: $" + product.getPrice() + ")");
        }
    }

    // Function to display all available customizable ingredients
    public void displayCustomizableIngredients() {
        System.out.println("Customizable Ingredients:");
        for (int i = 0; i < customizableIngredients.size(); i++) {
            Fruit ingredient = customizableIngredients.get(i);
            System.out.println((i + 1) + ". " + ingredient.getName() + " (Calories: " + ingredient.getCalories() + ", Price: $" + ingredient.getPrice() + ")");
        }
    }

    public void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Function to prepare a customizable product
    public boolean prepareCustomizableProduct(int productIndex, ArrayList<Integer> chosenIngredients, int payment) {
        Smoothie product = this.customizableProducts.get(productIndex - 1);
        double totalPrice = product.getPrice();
        ArrayList<String> addOns = new ArrayList<>();

        System.out.println("Preparing " + product.getName() + "...");
        System.out.println("adding ice to blender...");
        System.out.println("adding milk to blender...");

        for (Integer ingredientIndex : chosenIngredients) {
            Fruit ingredient = this.customizableIngredients.get(ingredientIndex - 1);
            System.out.println("adding " + ingredient.getName() + " to blender...");
            addOns.add(ingredient.getName());
            totalPrice += ingredient.getPrice();
        }

        if (payment >= totalPrice) {
            System.out.println(product.getName() + " Smoothie Done!");
            System.out.println("Add-ons: " + String.join(", ", addOns));
            System.out.println("Total Price: " + totalPrice);

            int change = payment - (int) totalPrice;
            ArrayList<Integer> changeList = this.getChange(change);
            ArrayList<Integer> denominations = this.getMoneyBox().getAvailableDenominations();
            int total = 0;

            for (int i = 0; i < changeList.size(); i++) {
                total += changeList.get(i) * denominations.get(i);
            }

            if (total == change) {
                System.out.println("Your change is: ");
                for (int i = 0; i < changeList.size(); i++) {
                    if (changeList.get(i) != 0) {
                        System.out.println(changeList.get(i) + " " + denominations.get(i) + " peso bills");
                    }
                }
            }
        } else {
            System.out.println("Insufficient payment, please enter a higher amount.");
        }
        return false;
    }


    // Overriding purchaseItem function to handle customizable products
    @Override
    public boolean purchaseItem(String name, int payment) {
        if (name.equals("1")) {
            System.out.println("Goodbye!");
            return true;
        } else if (name.matches("\\d+")) {
            int productIndex = Integer.parseInt(name);
            if (productIndex >= 1 && productIndex <= customizableProducts.size()) {
                displayCustomizableIngredients();
                System.out.println("Please enter the indices of the ingredients you want to include (separated by commas):");
                String input = scanner.nextLine();
                String[] chosenIndices = input.split(",");
                ArrayList<Integer> chosenIngredients = new ArrayList<>();
                for (String index : chosenIndices) {
                    int ingredientIndex = Integer.parseInt(index.trim());
                    if (ingredientIndex >= 1 && ingredientIndex <= customizableIngredients.size()) {
                        chosenIngredients.add(ingredientIndex);
                    } else {
                        System.out.println("Invalid ingredient index: " + index);
                    }
                }
                if (chosenIngredients.size() > 0) {
                    prepareCustomizableProduct(productIndex, chosenIngredients, payment);
                    return true;
                } else {
                    System.out.println("No valid ingredients chosen.");
                    return false;
                }
            } else {
                System.out.println("Invalid product index.");
                return false;
            }
        } else {
            return super.purchaseItem(name, payment);
        }
    }

    public ArrayList<Fruit> getCustomizableIngredients() {
        return customizableIngredients;
    }

    public ArrayList<Smoothie> getCustomizableProducts() {
        return customizableProducts;
    }
}