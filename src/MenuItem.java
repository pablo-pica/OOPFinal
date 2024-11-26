//encapsulates name and price of menu - encapsulation done - basically the parent class of every menu
public class MenuItem extends AbstractMenuItem {

    public MenuItem(String name, double price) {
        super(name, price);
    }

    public String toString() {
        return getName() + " - Php " + String.format("%.2f", getPrice());
    }
}