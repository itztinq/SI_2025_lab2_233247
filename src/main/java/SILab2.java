import java.util.List;

class Item {
    String name;
    int quantity; //numerical
    int price;
    double discount;

    public Item(String name, int quantity, int price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static double checkCart(List<Item> allItems, String cardNumber) { // START

        if (allItems == null){ // A
            throw new RuntimeException("allItems list can't be null!"); // B
        }
        double sum = 0; // C
        for (int i = 0; i < allItems.size(); i++) { // D1, D2, D3
            Item item = allItems.get(i); // E
            if (item.getName() == null || item.getName().length() == 0) { // E
                throw new RuntimeException("Invalid item!"); // F
            }
            if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10) { // G
                sum -= 30; // H
            }
            if (item.getDiscount() > 0) { // I
                sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity(); // J
            }
            else { // K
                sum += item.getPrice()*item.getQuantity(); // K
            }
        }
        if (cardNumber != null && cardNumber.length() == 16) { // L
            String allowed = "0123456789"; // M
            char[] chars = cardNumber.toCharArray(); // M
            for (int j = 0; j < cardNumber.length(); j++) { // N1, N2, N3
                char c = cardNumber.charAt(j); // O
                if (allowed.indexOf(c) == -1) { // O
                    throw new RuntimeException("Invalid character in card number!"); // P
                }
            }
        }
        else { // Q
            throw new RuntimeException("Invalid card number!"); // Q
        }
        return sum; // R

    } // END
}