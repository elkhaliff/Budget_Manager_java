package budget;

public class Purchase {
    Double price;
    String Name;

    public Purchase(String name, Double price) {
        this.price = price;
        Name = name;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return Name;
    }
}