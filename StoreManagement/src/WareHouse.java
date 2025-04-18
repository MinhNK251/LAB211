import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WareHouse implements Serializable{
    protected static int incrementCode = 0;
    protected int code;
    protected String type;
    protected LocalDateTime time;
    protected ArrayList<Product> list;

    public WareHouse() {
    }

    public WareHouse(int code) {
        this.code = ++incrementCode;
    }

    public WareHouse(int code, String type, LocalDateTime time, ArrayList<Product> list) {
        this.code = ++incrementCode;
        this.type = type;
        this.time = LocalDateTime.now();
        this.list = list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s Receipt || %07d || Time: %s",  getType(), getCode(), getTime()));
        for (Product p: list)
            result.append(" || ").append(p.toString());
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.code == ((WareHouse) obj).getCode();
    }
}
