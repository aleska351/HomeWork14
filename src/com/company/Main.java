package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Collection<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Alex", "Aa", "+3806698765430", 1990));
        contacts.add(new Contact("Anton", "Bb", "+3806698765431", 1991));
        contacts.add(new Contact("Oleg", "Cc", "+3806698765432", 1994));
        contacts.add(new Contact("Dima", "Ka", "+3806698765433", 2000));
        contacts.add(new Contact("Misha", "Nj", "+3806698765434", 1998));
        contacts.add(new Contact("Anna", "Fb", "+3806698765435", 1994));
        contacts.add(new Contact("Alina", "Aa", "+3806698765436", 1996));
        contacts.add(new Contact("Dasha", "Mo", "+3806698765437", 1991));
        contacts.add(new Contact("Masha", "Kit", "+3806698765438", 1992));
        contacts.add(new Contact("Roma", "Gin", "+3806698765439", 1998));

        writeToFile();
        writeToFileRandomNumber();
        readFileNumber();
        System.out.println("Записываем в файл контакты");
        writeToFileContact(contacts);
        System.out.println("Читаем из файла контакты, сортируем по году рождения и выводим первых 5");
        readFileContact();
        System.out.println("__________________________________________________________________________________________");
        serialize(contacts);
        deserialize();
    }

    /**
     * This method writes line "Hello World" to text file.
     */
    private static void writeToFile() {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("fileHelloWorld.txt"))) {
            writer.println("Hello, world!");
        } catch (IOException e) {
            System.err.println("При записи позникла ошибка " + e.getMessage());
        }
    }

    /**
     * This method creates a text file "fileNumber.txt" and writes 1000 random integers from -500 to 650, separated by commas, into it.
     */
    private static void writeToFileRandomNumber() {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("fileNumber.txt"))) {
            int[] arrayInteger = new int[1000];
            for (int array : arrayInteger) {
                array = (int) (Math.random() * (1150 + 1) - 500);
                writer.print(array + ", ");
            }
        } catch (IOException e) {
            System.err.println("При записи позникла ошибка " + e.getMessage());
        }
    }

    /**
     * This method opens a text file "fileNumber.txt" and reads the first 100 integers from it, separated by commas. Finds the average of read positive numbers.
     */
    private static void readFileNumber() {
        int sum = 0;
        int count = 0;
        List<Integer> values = new ArrayList<>();
        try (Scanner scanner = new Scanner(
                new FileInputStream("fileNumber.txt")).useDelimiter(", ")) {
            for (int i = 0; i < 100; i++) {
                values.add(scanner.nextInt());

            }
            for (Integer val : values) {
                System.out.print(val + ", ");
                if (val > 0) {
                    sum += val;
                    count++;
                }
            }
            System.out.println(" ");
            if (count != 0) {
                System.out.println(sum / count);
            } else
                System.out.println("Скорей всего эта ситуация нереальна, но с моей удачей бывает всякое:). Вообщем положительных чисел нет.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method saves a list of contacts (at least 10, each with a new line) in a text file "outputContact.txt" in the format:
     * First name / Last name / Phone / Year of birth.
     *
     * @param contacts - Collection of contacts
     */
    private static void writeToFileContact(Collection<Contact> contacts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("outputContact.txt"))) {
            for (Contact contact : contacts) {
                String line = String.format("%s/ %s/ %s/ %d/  ", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getYearBirth());
                writer.println(line);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * This method reads a list of contacts from a text file "outputContact.txt", sorts them by year of birth and outputs the first 5
     */

    private static void readFileContact() {
        List<Contact> contactes = new ArrayList<>();
        try (Scanner sc = new Scanner(
                new FileInputStream("outputContact.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Scanner scanner = new Scanner(line).useDelimiter("/ ");
                String name = scanner.next();
                String lastName = scanner.next();
                String number = scanner.next();
                int year = scanner.nextInt();
                contactes.add(new Contact(name, lastName, number, year));

            }
            Collections.sort(contactes, new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    return o1.getYearBirth() - o2.getYearBirth();
                }
            });
            int count = Math.min(5, contactes.size());
            for (int i = 0; i < count; i++) {
                System.out.println(contactes.get(i));
            }
            sc.close();
            System.out.println(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method serializes a collection of contacts and writes it to a file "fileContact.bin".
     *
     * @param contacts Collection of contacts.
     */
   static int countContacts;
    private static void serialize(Collection<Contact> contacts) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("fileContact.bin"))) {
            for (Contact contact : contacts) {
                os.writeObject(contact);
                countContacts++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method deserializes "fileContact.bin" in to the contact collection and displays the first 5 contacts sorted by year of birth.
     *
     * @return List contacts
     */
    private static List<Contact> deserialize() {
        List<Contact> contacts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fileContact.bin"))) {
            for (int i = 0; i < countContacts; i++) {
                contacts.add((Contact) ois.readObject());
            }
            Collections.sort(contacts, (o1, o2) -> o1.getYearBirth() - o2.getYearBirth());
            int count = Math.min(5, contacts.size());
            for (int i = 0; i < count; i++) {
                System.out.println(contacts.get(i));
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return contacts;
    }
}
