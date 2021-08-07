package App;

import Interface_form.ArrayList;
import Interface_form.SingleLinkedList;
import Test.Animal;
import Test.Cat;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) throws CloneNotSupportedException {
        Cat cat0 = new Cat(1, "aaa");
        Cat cat1 = new Cat(2, "bbb");
        Cat cat2 = new Cat(3, "ccc");
        Cat cat3 = new Cat(4, "ddd");
        Cat cat4 = new Cat(5, "eee");
        Cat cat5 = new Cat(1, "fff");
        Cat cat6 = new Cat(1, "ggg");
        Cat cat7 = new Cat(1, "hhh");
        Cat cat8 = new Cat(1, "iii");
        Cat cat9 = new Cat(1, "jjj");

        SingleLinkedList<Cat> cats = new SingleLinkedList<>();

        cats.add(cat0);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        cats.add(cat4);


        SingleLinkedList<Integer> original = new SingleLinkedList<>();
        original.add(10);	// original에 10추가

        SingleLinkedList<Integer> copy = original;
        SingleLinkedList<Integer> clone = (SingleLinkedList<Integer>) original.clone();

        copy.add(20);	// copy에 20추가
        clone.add(30);	// clone에 30추가

        System.out.println("original list");
        for(int i = 0; i < original.size(); i++) {
            System.out.println("index " + i + " data = " + original.get(i));
        }

        System.out.println("\ncopy list");
        for(int i = 0; i < copy.size(); i++) {
            System.out.println("index " + i + " data = " + copy.get(i));
        }

        System.out.println("\nclone list");
        for(int i = 0; i < clone.size(); i++) {
            System.out.println("index " + i + " data = " + clone.get(i));
        }

        System.out.println("\noriginal list reference : " + original);
        System.out.println("copy list reference : " + copy);
        System.out.println("clone list reference : " + clone);


        String str = "abc";
        //System.out.printf(reverse(str));

        System.out.printf(recursive(str));

    }

    private static String recursive(String str) {
        if (str.length() == 1) {
            return str;
        }

        return recursive(str.substring(1)) + str.charAt(0);
    }

    private static String reverse(String str) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = temp;
        }

        return toString(chars);
    }

    public static String toString(char[] chars) {
        StringBuilder sb = new StringBuilder(chars.length * 2);

        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
