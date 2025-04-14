public class Smartphone extends Product{
    private int price;
    private int internal_memory;
    private float weight;

    public Smartphone(String code, String name, int price, int internal_memory, float weight, String code, String name) {
        super(code, name);
        this.price = price;
        this.internal_memory = internal_memory;
        this.weight = weight;
    }

    public void showInfor(){
        System.out.print(code + "-" + name + "-" + internal_memory + "GB" + "-" + weight + "g" + "-" + price + "$");
    }
    
    public double buyNow(int quantity){
        double totalPayment = quantity * price;
        if(name.equals("IPHONE 14 PROMAX") && quantity >= 5)
            return totalPayment*0.8;
        return totalPayment;
    }
}
