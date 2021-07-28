package HomeWork4;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "616181251");
        phoneBook.add("Петров", "2871651");
        phoneBook.add("Сидоров", "27151796");
        phoneBook.add("Джонсон", "843453571");
        phoneBook.add("Андреев", "45636171");
        phoneBook.add("Андреев", "8234656453");
        phoneBook.add("Федоров", "421137567871");
        phoneBook.add("Смит", "123134556171");


        System.out.println(phoneBook.get("Андреев"));
    }

    private HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        ArrayList<String> phonesForLastName = phoneBook.getOrDefault(lastName, new ArrayList<>());
        phonesForLastName.add(phoneNumber);
        phoneBook.put(lastName, phonesForLastName);
    }

    public ArrayList<String> get(String lastName) {
        return phoneBook.get(lastName);
    }
}
