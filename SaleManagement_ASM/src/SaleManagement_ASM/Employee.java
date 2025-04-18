package SaleManagement_ASM;

import java.util.*;

public abstract class Employee {
    protected String name;
    protected String iD;
    protected Boolean gender;
    protected String dateOfBirth;
    protected long basicSalary;
    protected int salaryCoefficient;
    protected double realSalary;
    protected int seniority;

    public Employee() {
    }
    
    public Employee(String name, String iD, Boolean gender, String dateOfBirth, long basicSalary, int seniority) {
        this.name = name;
        this.iD = iD;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.basicSalary = basicSalary;
        this.seniority = seniority;
    }
    
    public double getRealSalary() {
        tinhLuong();
        return realSalary;
    }
    
    public int getSalaryCoefficient() {
        salaryCoefficient = 1 + (seniority/5);
        return salaryCoefficient;
    }

    public int getSeniority () {
        return seniority;
    }

    public void setSeniority(int seniority) {
        if (seniority < 0) return;
        this.seniority = seniority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getGender() {
        return gender?"Nam": "Nu";
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public long getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(long basicSalary) {
        this.basicSalary = basicSalary;
    }
    
    public abstract void tinhLuong ();
    
    public abstract void xuatThongTinNV ();
    
    public <T extends Employee> void input (ArrayList<T> list) {
        Scanner sc = new Scanner(System.in);
        Boolean inputInvalid;
        
        do {
            inputInvalid = false;
            try {
                System.out.print("Nhap ten: ");
                name = sc.nextLine();
                Validator.isNameValid(name);

                System.out.print("Nhap ma so: ");
                iD = sc.nextLine();
                Validator.isIDValid(iD);
                Validator.isIDUnique(iD, list);

                System.out.print("Nhap gioi tinh (true/false): ");
                String temp = sc.nextLine();
                Validator.isGender(temp);
                setGender(Boolean.valueOf(temp));

                System.out.print("Nhap ngay sinh (dd/mm/yyyy): ");
                dateOfBirth = sc.nextLine();
                Validator.isDateValid(dateOfBirth);
                Validator.isAdult(dateOfBirth);
                
                System.out.print("Nhap luong can ban: ");
                basicSalary = Long.parseLong(sc.nextLine());
                Validator.isNumPositive(basicSalary);

                System.out.print("Nhap tham nien: ");
                seniority = Byte.parseByte(sc.nextLine());
                Validator.isNumPositive(seniority);
                
            } catch (NumberFormatException e) {
                inputInvalid = true;
                System.err.println("Nhap sai dinh dang");
            } catch (Exception e) {
                inputInvalid = true;
                System.err.println(e.getMessage());
            }
        } while (inputInvalid);
    }
}
