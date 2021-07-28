package HomeWork4;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        String[] words = {"Nissan", "Audi", "BMW", "Cadillac", "Chevrolet", "Ford", "Honda", "Kia", "Jeep", "Opel",
                "Skoda", "Toyota", "Nissan", "BMW", "Kia", "Audi", "BMW", "Ford", "Kia", "Jeep"};

        HashMap<String, Integer> uniqueWords = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            if (uniqueWords.containsKey(words[i])) {
                uniqueWords.put(words[i], uniqueWords.get(words[i]) + 1);
            } else {
                uniqueWords.put(words[i], 1);
            }
        }

        System.out.println(uniqueWords);
    }
}
