// Programmer: Bryce Nyswonger
// Class: CS 145 
// IDE: VSCodium
// References: W3Schools, Youtube, Github, (ChatGPT as debugger)
import java.util.Scanner;

public class LetterInventory {
    private int[] counts;
    private int size;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LetterInventory inventory1 = null, inventory2 = null, result = null;
        while (true) {
            
            System.out.println("1- Enter String (Must do twice!)");
            System.out.println("2- Add inventories together");
            System.out.println("3- Subtract inventories");
            System.out.println("4- Show counts");
            System.out.println("5- Show letters (toString)");
            System.out.println("6- Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  
            switch (option) {
                case 1:
                    System.out.print("Enter the string for inventory: ");
                    String data = scanner.nextLine();
                    if (inventory1 == null) {
                        inventory1 = new LetterInventory(data);
                    } else if (inventory2 == null) {
                        inventory2 = new LetterInventory(data);
                    } else {
                        System.out.println("2 Inventories already created");
                    }
                    break;
                case 2:
                    if (inventory1 != null && inventory2 != null) {
                        result = inventory1.add(inventory2);
                        System.out.println(result);
                    } else {
                        System.out.println("You need to create 2 inventories");
                    }
                    break;
                case 3:
                    if (inventory1 != null && inventory2 != null) {
                        result = inventory1.subtract(inventory2);
                        System.out.println(result);
                    } else {
                        System.out.println("You need to create 2 inventories ");
                    }
                    break;
                case 4:
                    if (result != null) {
                        for (int i = 0; i < 26; i++) {
                            System.out.println((char)(i + 'a') + ": " + result.get((char)(i + 'a')));
                        }
                    } else {
                        System.out.println("Must add or subtract first");
                    }
                    break;
                case 5:
                    if (result != null) {
                        System.out.println( result);
                    } else {
                        System.out.println("Add or subtract first");
                    }
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }    
    }
    public LetterInventory(String data) {
        counts = new int[26];
        data = data.toLowerCase();

        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                counts[c - 'a']++;
                size++;
            }
        }
    }

    public int get(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("Invalid Input");
        }
        return counts[Character.toLowerCase(letter) - 'a'];
    }

    public void set(char letter, int value) {
        if (!Character.isLetter(letter) || value < 0) {
            throw new IllegalArgumentException();
        }

        int index = Character.toLowerCase(letter) - 'a';
        size = size - counts[index] + value;
        counts[index] = value;
    }

    public int size() {
        return size;
    }

   // public boolean isEmpty() {
       // return size == 0;
    //}

    
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append((char)(i + 'a'));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public LetterInventory add(LetterInventory other) {
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < 26; i++) {
            result.counts[i] = this.counts[i] + other.counts[i];
            result.size += result.counts[i];
        }
        return result;
    }

    public LetterInventory subtract(LetterInventory other) {
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < 26; i++) {
            if (this.counts[i] < other.counts[i]) {
                return null;
            }
            result.counts[i] = this.counts[i] - other.counts[i];
            result.size += result.counts[i];
        }
        return result;
    }
        
    
   
}
