import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductList extends ArrayList<Product> implements I_FunctionList {

    private ArrayList<Product> list = new ArrayList<>();
    private ArrayList<Product> warehouse = new ArrayList<>();
    private ArrayList<WareHouse> receipt = new ArrayList<>();
    private Product p;
    private WareHouse w;
    private int index;

    public ProductList() {
    }

    public ProductList(Product p, WareHouse w, int index) {
        this.p = p;
        this.w = w;
        this.index = index;
    }

    public ArrayList<Product> getList() {
        return list;
    }
    
    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    public ArrayList<Product> getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(ArrayList<Product> warehouse) {
        this.warehouse = warehouse;
    }

    public ArrayList<WareHouse> getReceipt() {
        return receipt;
    }

    public void setReceipt(ArrayList<WareHouse> receipt) {
        this.receipt = receipt;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public WareHouse getW() {
        return w;
    }

    public void setW(WareHouse w) {
        this.w = w;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public void add() {
        do {
            p = new Product();
            boolean check;
            do{
                check = false;
                p.createProduct();
                for (Product q : list)
                    if (p.getProductCode().equals(q.getProductCode())){
                        check = true;
                        System.out.println("Product code is dupplicated, create again!");
                        break;
                    }
            } while(check);
            list.add(p);
            System.out.println("Product added successfully!");
        } while (Utils.getYesNo("Continue? (Yes/No): "));
    }

    @Override
    public int find(String code) {
        index = list.indexOf(new Product(code));
        return index;
    }

    @Override
    public void delete() {
        this.index = find(Utils.getString("Enter product ID that you want to delete: "));
        boolean check;
        if (index != -1) {
            check = Utils.getYesNo("Are you sure you want to delete (Yes/No): ");
            if (check) {
                list.remove(index);
                System.out.println("Delete Success!\n");
            } else {
                System.out.println("Delete Fail!\n");
            }
        } else {
            System.out.println("No product found!\n");
        }
    }

    @Override
    public void update() {
        this.index = find(Utils.getString("Enter the product code that you want to update: "));
        if (index != -1) {
            p = list.get(index);
            p.updateProduct();
            list.set(index, p);
            System.out.println("Product updated!");
            System.out.println("The result of the update: \n" + p.toString());
        } else {
            System.out.println("No product found!");
        }
    }

    @Override
    public void output() {
        if (list.isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        for (Product p : list)
            System.out.println(p.toString());
    }

    @Override
    public void writeFile() {
        String[] filename = new String[]{"product.dat", "warehouse.dat"};
        for (String name : filename) {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(name);
                oos = new ObjectOutputStream(fos);
                if(name.equals("product.dat"))
                    oos.writeObject(list);
                else oos.writeObject(receipt);
                System.out.println(name + " Saved!");
            } catch (FileNotFoundException ex) {
                    System.err.println(name + " not found!");
            } catch (IOException ex) {
                    System.err.println("IOException!");
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ex) {
                        System.err.println("IOException!");
                    }
                }
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        System.err.println("IOException!");
                    }
                }
            }
        }
    }
    
    public void loadFile() {
        String[] filename = new String[]{"product.dat", "warehouse.dat"};
        for (String name : filename) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(name);
                ois = new ObjectInputStream(fis);
                if(name.equals("product.dat"))
                    list = (ArrayList<Product>) ois.readObject();
                else receipt = (ArrayList<WareHouse>) ois.readObject();
                    System.out.println(name + " Loaded!");
            } catch (FileNotFoundException ex) {
                    System.err.println(name + " not found!");
            } catch (IOException ex) {
                    System.err.println("IOException!");
            } catch (ClassNotFoundException ex) {
                    System.err.println("Class not found!");
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                            System.err.println("IOException!");
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                            System.err.println("IOException!");
                    }
                }
            }
        }
    }
    
    public void addReceiptItem() {
        do {
            p = new Product();
            boolean check;
            do{
                check = false;
                p.createProduct();
                for (Product q : warehouse)
                    if (p.getProductCode().equals(q.getProductCode())){
                        check = true;
                        System.out.println("Product code is dupplicated, create again!");
                        break;
                    }
            } while(check);
            warehouse.add(p);
            System.out.println("Product added successfully!");
        } while (Utils.getYesNo("Continue? (Yes/No): "));
    }
    
    public void createReceipt(String str){
        w = new WareHouse();
        addReceiptItem();
        if(str.equals("Import")){
            w.setType("Import");
            w.setList(warehouse);
            receipt.add(w);
            warehouse.clear();
        }
        else{
            w.setType("Export");
            w.setList(warehouse);
            receipt.add(w);
            warehouse.clear();
        }
        System.out.println(str + " receipt created successfully!");
    }
    
    public void expireList() throws ParseException{
        List<Product> expiredProducts = new ArrayList();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Product p: list) {
            if (LocalDate.parse(p.getExpirationDate(), dateTimeFormat).isBefore(LocalDate.parse(p.getManufacturingDate(), dateTimeFormat)))
                expiredProducts.add(p);
        }
        for (Product p: expiredProducts)
                System.out.println(p.toString());
    }
    
    public void sellingList() throws ParseException{
        List<Product> sellingProducts = new ArrayList();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Product p: list) {
            if (LocalDate.parse(p.getManufacturingDate(), dateTimeFormat).isBefore(LocalDate.parse(p.getExpirationDate(), dateTimeFormat))&&p.getQuantity()>=0) {
                sellingProducts.add(p);
            }
        }
        for (Product p: sellingProducts)
                System.out.println(p.toString());
    }
    
    public void outOfStock() throws ParseException{
        List<Product> outOfStockList = new ArrayList();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Product p: list)
            if (LocalDate.parse(p.getManufacturingDate(), dateTimeFormat).isBefore(LocalDate.parse(p.getExpirationDate(), dateTimeFormat))
                    && p.getQuantity()>=0)
                outOfStockList.add(p);
            Collections.sort(outOfStockList, new Comparator<Product>() {
                @Override
                public int compare(Product c1, Product c2) {
                    if (c1.getQuantity() > c2.getQuantity())
                        return 1;
                    else {
                        if (c1.getQuantity() == c2.getQuantity())
                            return 0;
                        return -1;
                    }
                }
            });
        for (Product p : outOfStockList)
                System.out.println(p.toString());
    }
    
    public void importExport(){
        if (receipt.isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        int count = 0;
        String code = Utils.getString("Product code: ");
        for (WareHouse wh : receipt)
            for(Product p: wh.getList())
                if(p.getProductCode().equals(code)){
                    System.out.println("Receipt Type: " + wh.getType() +" || Receipt Code: "+ wh.getCode()+ "|| Time created: "+ wh.getTime());
                    System.out.println("Product: " + p.toString());
                    count++;
                }
        if(count == 0)
            System.out.println("Product does not exist");
        System.out.println("");
    }
}