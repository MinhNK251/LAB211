package SaleManagement_ASM;

import java.util.*;

public class Business extends Employee{
    private long sales;
    private float commission;

    public Business () {
    }
    
    public Business(long sales, String name, String iD, Boolean gender, String dateOfBirth, long basicSalary, int seniority) {
        super(name, iD, gender, dateOfBirth, basicSalary, seniority);
        this.sales = sales;
    }

    public float getCommission() {
        if (sales >= 20000000) 
            commission = 0.2f;
        else if (sales >= 10000000) 
            commission = 0.1f;
        else if (sales >= 5000000) 
            commission = 0.05f;
        else commission = 0;
        return commission;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        if (sales < 0) return;
        this.sales = sales;
    }
    
    @Override
    public double getRealSalary() {
        tinhLuong();
        return realSalary;
    }
    
    @Override
    public void tinhLuong() {
        realSalary = basicSalary * getSalaryCoefficient() + sales * getCommission();
    }

    @Override
    public void xuatThongTinNV() {
        System.out.printf("Ten: %s\nMa so: %s\nGioi tinh: %s\nNgay sinh: %s\nLuong thuc lanh: %f\nTham nien: %s\nDoanh so: %d\n",
        getName(), getiD(), getGender(), getDateOfBirth(), getRealSalary(), getSeniority(), getSales());
    }

    @Override
    public <T extends Employee> void input (ArrayList<T> list) {
        Scanner sc = new Scanner(System.in);
        Boolean inputInvalid;
        super.input(list);
        do {
            inputInvalid = false;
            try {
                System.out.print("Nhap doanh so: ");
                sales = sc.nextLong();
                Validator.isNumPositive(sales);
            } catch (Exception e) {
                inputInvalid = true;
                System.err.println(e.getMessage());
            }
        } while (inputInvalid);
    }
    
}
