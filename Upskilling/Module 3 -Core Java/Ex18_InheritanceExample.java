class Animal {
    void makeSound() {
        System.out.println("Some generic animal sound...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark!");
    }
}

public class Ex18_InheritanceExample {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.makeSound();   

        Dog dog = new Dog();
        dog.makeSound();    


        Animal polyDog = new Dog();
        polyDog.makeSound();  
    }
}