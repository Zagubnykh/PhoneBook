package practice;

import java.util.*;

public class PhoneBook {
    private TreeMap<String, String> phoneBook;
    public static final String WRONG_INPUT_ANSWER = "Неверный формат ввода";
    public static final String SAVED_ANSWER = "Контакт сохранен!";

    public PhoneBook() {
        this.phoneBook = new TreeMap<>();
    }

    public boolean isValidName(String name) {
        return name.matches("^[а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+ ?[а-яА-ЯёЁa-zA-Z]+$");
    }

    public boolean isValidPhone(String phone) {
        String regex = "[^0-9]";
        phone = phone.replaceAll(regex, "");
        return phone.matches("[7-8][0-9]{10}");
    }

    public void addContact(String phone, String name) {
        // проверка корректности формата имени и телефона
        // если такой номер уже есть в списке, то перезаписывается имя абонента
        if (isValidPhone(phone) && isValidName(name)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                String currentName = entry.getKey();
                String currentPhone = entry.getValue();
                if (currentPhone.equals(phone)) {
                    phoneBook.remove(currentName);
                }
            }
            if (phoneBook.containsKey(name)) {
                phone = phoneBook.get(name).concat(", " + phone);
            }
            phoneBook.put(name, phone);
            System.out.println(SAVED_ANSWER);
        }  else {
            System.out.println(WRONG_INPUT_ANSWER);
        }
    }

    public String getContactByPhone(String phone) {
        // если контакт не найдены - возвращается пустая строка
        if (phoneBook.containsValue(phone)) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()){
                String name = entry.getKey(); // получения ключа
                String currentPhone = entry.getValue(); // получения значения
                if (currentPhone.equals(phone)) {
                    return name + " - " + phone;
                }
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        TreeSet<String> contactByName = new TreeSet<>();
        // если контакт не найден - возвращается пустой TreeSet
        if (phoneBook.containsKey(name)) {
            contactByName.add(name + " - " + phoneBook.get(name));
        }
        return contactByName;
    }

    public Set<String> getAllContacts() {
        // если контактов нет в телефонной книге - возвращается пустой TreeSet
        TreeSet<String> allContacts = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()){
            String name = entry.getKey(); // получения ключа
            String phone = entry.getValue(); // получения значения
            allContacts.add(name + " - " + phone);
        }
        return allContacts;
    }
}