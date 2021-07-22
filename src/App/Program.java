package App;

import Interface_form.ArrayList;
import Test.Animal;
import Test.Cat;

public class Program {

    public static void main(String[] args) {
        Cat cat0 = new Cat(1, "aaa");
        Cat cat1 = new Cat(1, "bbb");
        Cat cat2 = new Cat(1, "ccc");
        Cat cat3 = new Cat(1, "ddd");
        Cat cat4 = new Cat(1, "eee");
        Cat cat5 = new Cat(1, "fff");
        Cat cat6 = new Cat(1, "ggg");
        Cat cat7 = new Cat(1, "hhh");
        Cat cat8 = new Cat(1, "iii");
        Cat cat9 = new Cat(1, "jjj");

        ArrayList<Animal> cats = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            cats.add(cat0);
        }

        ArrayList<Cat> cats1 = new ArrayList<>(5);
        cats1.add(cat0);
        cats1.add(cat1);
        cats1.add(cat2);
        cats1.add(cat3);
        cats1.add(cat4);
        cats1.add(cat5);

        for (int i = 0; i < cats1.size(); i++) {
            cats1.get(i).shout();
        }

        boolean isBool = cats1.contains(cat2);
        assert isBool;

        isBool = cats1.contains(cat6);
        assert !isBool;
    }
}
