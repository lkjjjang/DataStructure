package Test;

public abstract class Animal {
    private final int age;
    private final String name;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void shout() {
        System.out.printf("my name is %s%s", this.name, System.lineSeparator());
    }

    public String toString() {
        return String.format("age : %d name : %s ", this.age, this.name);
    }
}
