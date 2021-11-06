import org.apache.commons.lang3.ArrayUtils;
import pl.coderslab.ConsoleColors;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class TaskManager {
    static String[][] tasks;



    public static void main(String[] args) {
        tasks = filesLoad();
        menu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String rob = scanner.nextLine();

            switch (rob) {
                case "list":
                    list(tasks);
                    break;
                case "add":
                    add();
                    break;
                case "remove":
                    remove(tasks, index());
                    break;
                case "exit":
                    exitSave();
                    break;
                default:
                    System.out.println("Please select a correct option");
            }
            menu();
        }
    }


    public static void exitSave() {
        try {
            PrintWriter printWriter = new PrintWriter("tasks.csv");

        for (int i = 0; i < tasks.length; i++) {
            printWriter.println(tasks[i][0] + "," + tasks[i][1] + "," + tasks[i][2]);
        }
        printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static int index() {
        System.out.println("Choose index number to remove");
        Scanner scanner = new Scanner(System.in);
        int wynik = scanner.nextInt();
        while (!(wynik >= 0)) {
            System.out.println("Incorrect index number, type number greater or equal 0");
            scanner.nextInt();
        }
        return wynik;
    }


    public static void remove(String[][] arr, int nr) {
        try {
            if (nr < arr.length) {
                tasks = ArrayUtils.remove(tasks, nr);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such element");
        }
    }


    public static void add() {
        //metoda dodajaca zadania do managera
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String desc = scanner.nextLine();
        System.out.println("Please add task due date (yyyy-mm-dd");
        String date = scanner.nextLine();
        System.out.println("Is your task important: true/false?");
        String importance = scanner.nextLine();
        //wczytywanie poszczególnych elementów
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = desc;
        tasks[tasks.length - 1][1] = " " + date;
        tasks[tasks.length - 1][2] = " " + importance;
        //zapisywanie ich do kolejnych indexów naszej tablicy
    }


    public static String[][] filesLoad() {
        //metoda zapisujaca plik do tablicy
        Path path = Paths.get("tasks.csv");
        String[][] wynik = null;
        try {
            List<String> strings = Files.readAllLines(path);
            wynik = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    wynik[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    public static void list(String[][] arr) {
        //metoda odpowiedzialna za wyswietlanie elementow naszej talbicy
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    //pierwsza wersja metody list

//    public static void list() {
//        Path path = Paths.get("src", "main", "java", "tasks.csv");
//        File file = new File(String.valueOf(path));
//        try {
//            Scanner scanfile = new Scanner(file);
//            String line = "";
//
//            while (scanfile.hasNextLine()) {
//                line = scanfile.nextLine();
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie znaleziono pliku");
//        }
//    }


    public static void menu() {
        //metoda do wyswietlania menu glownego managera
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println(ConsoleColors.RED + "exit");
        System.out.println(ConsoleColors.RESET);
    }
}
