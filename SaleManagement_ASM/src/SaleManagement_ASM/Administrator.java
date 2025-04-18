package SaleManagement_ASM;

import java.util.*;

public class Administrator extends Employee{
    private long allowance;

    public Administrator() {
    }

    public Administrator(long allowance, String name, String iD, Boolean gender, String dateOfBirth, long basicSalary, int seniority) {
        super(name, iD, gender, dateOfBirth, basicSalary, seniority);
        this.allowance = allowance;
    }

    public long getAllowance() {
        return allowance;
    }

    public void setAllowance(long allowance) {
        if (allowance < 0) return;
        this.allowance = allowance;
    }

    @Override
    public double getRealSalary() {
        tinhLuong();
        return realSalary;
    }

    @Override
    public void tinhLuong() {
        realSalary = basicSalary * getSalaryCoefficient() + allowance;
    }

    @Override
    public void xuatThongTinNV() {
        System.out.printf("Ten: %s\nMa so: %s\nGioi tinh: %s\nNgay sinh: %s\nLuong thuc lanh: %f\nTham nien: %s\nPhu cap: %d\n",
        getName(), getiD(), getGender(), getDateOfBirth(), getRealSalary(), getSeniority(), getAllowance());
    }
    
    @Override
    public <T extends Employee> void input (ArrayList<T> list) {
        Scanner sc = new Scanner(System.in);
        Boolean inputInvalid;
        super.input(list);
        do {
            inputInvalid = false;
            try {
                System.out.print("Nhap phu cap: ");
                allowance = sc.nextLong();
                Validator.isNumPositive(allowance);
            } catch (Exception e) {
                inputInvalid = true;
                System.err.println(e.getMessage());
            }
        } while (inputInvalid);
    }
}
