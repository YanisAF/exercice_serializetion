package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final String BINARY_FILE_CHARACTERS = "characters.dat";
    private static final String BINARY_FILE_MONSTERS = "monsters.txt";
    private static final String FILE_EVENTS = "events.txt";
    private static final String FILE_JOURNAL = "journal.txt";

    private List<String> journal = new ArrayList<>();
    private List<Characters> characters;
    private List<Monsters> monsters;

    public Game() {
        createJournal();
        characters = new ArrayList<>();
        loadCharacters();
    }

    public void run(){
        while(true){
            System.out.println("--- Bienvenue dans le jeu d'aventure textuelle ! ---");
            System.out.println("1. Créer un nouveau personnage");
            System.out.println("2. Charger un personnage existant");
            System.out.print("Choisissez une option : ");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option){
                case 1:
                    createCharacters(scanner);
                    saveCharacters();
                    break;
                case 2:
                    choiceCharacter(scanner);
                default:
                    System.out.println("Choisissiez une option valide");
            }
        }
    }

    private List<Characters> loadCharacters(){
        List<Characters> characters = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(BINARY_FILE_CHARACTERS))) {
            characters = (List<Characters>) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return characters;
    }

    private List<Monsters> loadMonsters(){
        List<Monsters> monsters = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(BINARY_FILE_MONSTERS))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] monster = line.split(",");
                String name = monster[0].trim();
                int strength = Integer.parseInt(monster[1].trim());
                int health = Integer.parseInt(monster[2].trim());
                monsters.add(new Monsters(name, strength, health));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return monsters;
    }

    private List<String> loadEvents(){
        List<String> events = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_EVENTS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                events.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return events;
    }

    private void saveJournal(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_JOURNAL, true))){
            for (String entry : journal) {
                writer.write(entry);
                writer.newLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createCharacters(Scanner scanner){
        System.out.println("Enter the name of the character : ");
        String name = scanner.nextLine();

        System.out.println("Enter the force of character : ");
        int force = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the health of the character : ");
        int health = scanner.nextInt();

        characters.add(new Characters(name, force, health));
        System.out.println("Personnage créé avec succès et sauvegardé.");
    }

    public void saveCharacters(){
        File file = new File(BINARY_FILE_CHARACTERS);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(characters);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void choiceCharacter(Scanner scanner){
        System.out.println("Enter the name of the character : ");
        String name = scanner.nextLine();
        try(FileInputStream fileIn = new FileInputStream(BINARY_FILE_CHARACTERS);
            ObjectInputStream out = new ObjectInputStream(fileIn)){
            characters = (List<Characters>)out.readObject();
            characters.stream()
                    .filter(n -> n.getName().equals(name)).forEach(System.out::println);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }



}
