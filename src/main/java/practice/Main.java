package practice;

import java.util.Scanner;

public class Main {
    public static final String INFO_MESSAGE = "Введите номер, имя или команду:";
    public static final String WRONG_INPUT_ANSWER = "Неверный формат ввода";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        String name = "";
        String phone = "";

        while (true) {
            System.out.println(INFO_MESSAGE);
            String input = scanner.nextLine();
            if (input.equals("LIST")) {
                System.out.println(phoneBook.getAllContacts().toString());
                break;
            } else if (phoneBook.isValidPhone(input)) {
                phone = input;
                if (phoneBook.getContactByPhone(phone).isEmpty()) {
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println("Введите имя абонента для номера " + '"' + phone + '"');
                    input = scanner.nextLine();
                    name = input;
                    phoneBook.addContact(phone, name);
                }
            } else if (phoneBook.isValidName(input)) {
                name = input;
                if (phoneBook.getContactByName(name).isEmpty()) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите номер телефона для абонента " + '"' + name + '"');
                    input = scanner.nextLine();
                    phone = input;
                    phoneBook.addContact(phone, name);
                }
            } else {
                System.out.println(WRONG_INPUT_ANSWER);
                break;
            }
        }
    }
}
