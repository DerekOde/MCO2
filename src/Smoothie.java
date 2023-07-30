import java.util.ArrayList;

public class Smoothie extends Item{
    private String name;
    private int calories;
    private double price;
    private ArrayList<Fruit> fruits;

    public Smoothie(String name, int calories, double price) {
        super("Smoothie", calories, price);
        this.name = name;
        this.fruits = new ArrayList<>();
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    public void removeFruit(Fruit fruit) {
        fruits.remove(fruit);
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        int totalCalories = 0;
        for (Fruit fruit : fruits) {
            totalCalories += fruit.getCalories();
        }
        return totalCalories;
    }

    public double getTotalCalories() {
        double totalCalories = 0;
        for (Fruit fruit : fruits) {
            totalCalories += fruit.getCalories();
        }
        return totalCalories;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Fruit fruit : fruits) {
            totalPrice += fruit.getPrice();
        }
        return totalPrice;
    }

    public double getPrice() {
        double totalPrice = 0;
        for (Fruit fruit : fruits) {
            totalPrice += fruit.getPrice();
        }
        return totalPrice;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }
}