class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
    }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}
public class Q18_InheritanceExample {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.makeSound();
        Dog dog = new Dog();
        dog.makeSound();
    }
}