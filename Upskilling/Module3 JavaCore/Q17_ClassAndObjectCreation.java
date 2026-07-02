class Car {
    String make;
    String model;
    int year;
    public Car(String make, String model, int year) {
        this.make = make; this.model = model; this.year = year;
    }
    public void displayDetails() {
        System.out.println("Car Details: " + year + " " + make + " " + model);
    }
}
public class Q17_ClassAndObjectCreation {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Corolla", 2021);
        myCar.displayDetails();
    }
}