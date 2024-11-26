import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//user input storage
class Order {
    private ArrayList<MenuItem> items;
    int orderNo = 1;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    /*
     * desecrated code
     * public String toString(){
     * String order = "";
     * for (MenuItem item : items){
     * order += item + "\n";
     * }
     * return order;
     * }
     */

    public void saveReceipt() {
        if (items.size() == 0) {
            System.out.println("No items in cart.");
            return;
        }

        // file handling, can find it in the package folder idk
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "receipt_" + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("*****************************************************************\n");
            writer.write("*                            RECEIPT                            *\n");
            writer.write("*****************************************************************\n");
            writer.write("Order No. " + orderNo + "\n");
            // save date and time to receipt
            writer.write("Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
            writer.write("-----------------------------------------------------------------\n");
            writer.write("Items:\n");

            // list of orders in receipt
            for (MenuItem item : items) {
                writer.write(String.format("| %-49s Php %7.2f |\n", item.getName(), item.getPrice()));
            }

            writer.write("-----------------------------------------------------------------\n");

            // total
            writer.write(String.format("| %-49s Php %7.2f |\n", "Total:", calculateTotal()));

            writer.write("*****************************************************************\n");
            writer.write("*                  THANK YOU FOR YOUR PURCHASE                  *\n");
            writer.write("*****************************************************************\n");

            System.out.println("Receipt saved as " + fileName);
            orderNo++;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}