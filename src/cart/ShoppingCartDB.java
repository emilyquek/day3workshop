package cart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCartDB {

    public String cartOwner;
    public String getCartOwner() {return cartOwner;}
    public void setCartOwner(String cartOwner) {this.cartOwner = cartOwner;}

    List<String> itemsInCart = new ArrayList<>();

    public ShoppingCartDB(String ownerName){
        this.cartOwner = ownerName;
    }

    public void loadCart() throws IOException{
        String fileName = "/Users/emilyquek/Desktop/vttp/workshop3/cartdb/"+this.cartOwner+".txt";
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
            System.out.printf("%s, your cart is empty!\n", cartOwner);
        } else {
            Reader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            breader.readLine();
            String line1 = null;
            List<String> alreadyInCart = new ArrayList<>();
            while ((line1 = breader.readLine()) != null) {
                alreadyInCart.add(line1);
            }
            if (!alreadyInCart.isEmpty()) {
                System.out.println(cartOwner+", your cart contains the following items:");
                for (int i = 0; i < alreadyInCart.size(); i++)
                    System.out.printf("%d. %s\n", i+1, alreadyInCart.get(i).trim());
            } else {
                System.out.printf("%s, your cart is empty!\n", cartOwner);
            } 
        
            breader.close();
        }
    }

    public void saveCart(List<String> userCart) throws IOException{
        if (this.cartOwner != null) {
            String fileName = this.cartOwner+".txt";
            File toSave = new File("/Users/emilyquek/Desktop/vttp/workshop3/cartdb", fileName);

            Reader reader = new FileReader(toSave);
            BufferedReader breader = new BufferedReader(reader);

            if (breader.readLine()==null) {

                Writer writer = new FileWriter(toSave);
                BufferedWriter bwriter = new BufferedWriter(writer);
                
                bwriter.write(toSave.toString());
                bwriter.newLine();
                for (String cartContent : userCart){
                    bwriter.write(cartContent);
                    bwriter.newLine();
                } 
                bwriter.flush();
                bwriter.close();
                System.out.println("Your cart has been saved!");
            }
            breader.close();
        } else {
            System.out.println("Please login first before proceeding!");
        } 
    }
}
