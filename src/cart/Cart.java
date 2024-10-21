package cart;

import java.io.*;
import java.util.*;

public class Cart {
        public static void main(String[] args) throws IOException, FileNotFoundException {

        File db = new File("cartdb");

        if (args.length <= 0){
          db.mkdir();
        } else if (!args[0].equals("cartdb")) {
            String newFilePath = args[0];
            File newdb = new File(newFilePath);
        } else {
            File cartdb = new File("cartdb");
        } 

        System.out.println("Welcome to your shopping cart!");

        boolean stop = false;

        Console cons = System.console();

        List<String> itemsInCart = new ArrayList<>();

        ShoppingCartDB cartInit = new ShoppingCartDB("");



        while (!stop) {
            String[] command = cons.readLine("What would you like to do? ").trim().split("[\\s,]+");

            switch (command[0].toLowerCase()) {
                case ("login"):
                    cartInit = new ShoppingCartDB(command[1]);
                    //user = command[1];
                    cartInit.loadCart();
                    break;

                case ("save"):
                    cartInit.saveCart(itemsInCart);
                    itemsInCart.clear();
                    break;
                
                case ("users"):
                    String directoryPath = "/Users/emilyquek/Desktop/vttp/workshop3/cartdb";
                    File directory = new File(directoryPath);
                    File[] files = directory.listFiles();
                    System.out.println("The following users are registered:");
                        for (File file : files){
                            for (int i = 1; i < files.length; i++)
                                System.out.printf("%d. %s\n", i, file.getName().substring(0, file.getName().length()-4));
                        }
                    break;

                case ("list"): 
                    if (!itemsInCart.isEmpty()) {
                        System.out.println("Your cart contains:"); 
                        for (int i = 0; i < itemsInCart.size(); i++) {
                            System.out.printf("%d. %s\n", i+1, itemsInCart.get(i));
                        }
                    } else {
                        System.out.println("Your cart is empty!");
                    }
                    break;
                    
                case ("add"):
                    File existingCart = new File ("/Users/emilyquek/Desktop/vttp/workshop3/cartdb/"+cartInit.getCartOwner()+".txt");
                        if (existingCart.exists()) {
                            Reader reader = new FileReader(existingCart);
                            BufferedReader bReader = new BufferedReader(reader);
                            bReader.readLine();
                            String line1 = null;

                            while ((line1 = bReader.readLine()) != null) {
                                itemsInCart.add(line1);
                            } 
                            bReader.close();
                        
                            for (int idx = 1; idx < command.length; idx++) {
                                if (itemsInCart.toString().contains(command[idx])) {
                                    {System.out.println("Item already in cart!");}
                                } else {
                                    itemsInCart.add(command[idx]);
                                    System.out.printf("%s added to cart\n", command[idx]);
                                }
                            }

                        } else {
                            for (int idx = 1; idx < command.length; idx++) {
                                if (itemsInCart.toString().contains(command[idx])) {
                                    {System.out.println("Item already in cart!");}
                                } else {
                                    itemsInCart.add(command[idx]);
                                    System.out.printf("%s added to cart\n", command[idx]);
                                }
                            }
                        }
                    break;

                case ("delete"):
                    try {
                    System.out.printf("%s removed from cart\n", itemsInCart.get(Integer.parseInt(command[1])-1));
                    itemsInCart.remove(Integer.parseInt(command[1])-1);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("Incorret item index!");
                    }
                    break;

                case ("stop"):
                    stop = true;
                    break;
            }
        }
    }
}