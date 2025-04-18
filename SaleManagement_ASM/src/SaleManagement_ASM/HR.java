package SaleManagement_ASM;

import java.util.*;

public class HR {
    ArrayList<Employee> employeeList;
    
    public HR () {
        employeeList = new ArrayList<>();
    }
    
    public void nhapDS (Employee newEmployee) {
        employeeList.add(newEmployee);
    }
    
    public void xuatToanBoDS () {
        if (employeeList.isEmpty())
            System.out.print("Danh sach trong\n");
        else {
            int count = 0;
            for (Employee person: employeeList) {
                System.out.printf("\n%d. %s\n", ++count, person.getClass().getSimpleName());
                person.xuatThongTinNV();
            }
        }
    }
    
    public void xuatNVTheoLoai (String loaiNV) {
        if (employeeList.isEmpty())
            System.out.print("Danh sach trong\n");
        else {
            int count = 0;
            for (Employee person: employeeList) {
                switch(loaiNV) {
                    case "Business":
                        if (person instanceof Business) {
                            System.out.printf("\n%d. ", ++count);
                            person.xuatThongTinNV();
                        }
                        break;
                    case "Administrator":
                        if (person instanceof Administrator) {
                            System.out.printf("\n%d. ", ++count);
                            person.xuatThongTinNV();
                        }
                        break;
                }
            }
        }
    }
    
    public void tinhLuongTB () {
        if (employeeList.isEmpty())
            System.out.print("Luong trung binh toan cong ty: 0\n");
        else {
            double totalSalary = 0;
            for (Employee person: employeeList) {
                totalSalary += person.getRealSalary();
            }
            System.out.printf("Luong trung binh toan cong ty: %f\n", (totalSalary / employeeList.size()));
        }
    }
    
    public void timLuongCaoNhat () {
        int indexHighestSalary = 0;

        if (employeeList.isEmpty())
            System.out.print("Nhan vien khong ton tai\n");
        else {
            for (Employee person: employeeList) {
                if (employeeList.get(indexHighestSalary).getRealSalary() < person.getRealSalary()) 
                    indexHighestSalary = employeeList.indexOf(person);
            }
            System.out.print("Nhan vien co luong thuc lanh cao nhat:\n");
            employeeList.get(indexHighestSalary).xuatThongTinNV();
        }
    }
    
    public void timLuongThapNhat () {
        int indexLowestSalary = 0;

        if (employeeList.isEmpty())
            System.out.print("Nhan vien khong ton tai\n");
        else {
            for (Employee person: employeeList) {
                if (employeeList.get(indexLowestSalary).getRealSalary() > person.getRealSalary()) 
                    indexLowestSalary = employeeList.indexOf(person);
            }
            System.out.print("Nhan vien co luong thuc lanh thap nhat:\n");
            employeeList.get(indexLowestSalary).xuatThongTinNV();
        }
    }
    
    public void timKiemNV (String nameID) {
        int index = -1;
        for (Employee person: employeeList) {
            if (person.getName().equals(nameID) || person.getiD().equals(nameID))
                index = employeeList.indexOf(person);
            }
        if (index != -1)
            employeeList.get(index).xuatThongTinNV();
        else System.out.print("Nhan vien khong ton tai\n");
    }
    
    public void sapXepNV () {
        if (employeeList.isEmpty())
            System.out.print("Danh sach trong\n");
        else {
            for (int i = 0; i < employeeList.size() - 1; i++) {
                for (int j = i + 1; j < employeeList.size(); j++) {
                    int dif = employeeList.get(i).getName().compareToIgnoreCase(employeeList.get(j).getName());
                    if (dif > 0)
                        Collections.swap(employeeList, i, j);
                    else if (dif == 0) 
                        if (employeeList.get(i).getRealSalary() < employeeList.get(j).getRealSalary())
                            Collections.swap(employeeList, i, j);
                }
            }
            System.out.printf("Sap xep thanh cong\n");
        }
    }
    
    public void xoaNVTheoMa (String maNV) {
        int removeIndex = -1;
        if (Validator.isIDValid(maNV)){
            for (Employee person: employeeList) {
                if (person.getiD().equals(maNV))
                    removeIndex = employeeList.indexOf(person);
            }
        }
        if (removeIndex != -1){
            employeeList.remove(removeIndex);
            System.out.print("Nhan vien da bi xoa\n");
        }
        else System.out.print("Nhan vien khong ton tai\n");
    }
    
    public void capNhatNVTheoMa (String maNV) {
        int updateIndex = -1;
        if (Validator.isIDValid(maNV)){
            for (Employee person: employeeList) {
                if (person.getiD().equals(maNV))
                    updateIndex = employeeList.indexOf(person);
            }
        }
        if (updateIndex != -1) {
            Employee person;
            if (employeeList.get(updateIndex) instanceof Business)
                person = new Business();
            else person = new Administrator();
            person.input(employeeList);
            employeeList.set(updateIndex, person);
        }
        else System.out.print("Nhan vien khong ton tai\n");
    }
    
    public static void main(String[] args) {
        HR humanResource = new HR();
        do {
            try {
                System.out.println("""
                               \n________________MENU________________
                               1. Nhap danh sach cac nhan vien
                               2. Xuat toan bo danh sach nhan vien
                               3. Xuat danh sach nhan vien theo loai
                               4. Tinh luong trung binh toan cong ty
                               5. Tim nhan vien co luong thuc lanh cao nhat
                               6. Tim nhan vien co luong thuc lanh thap nhat
                               7. Tim kiem nhan vien dua theo ma so/ten
                               8. Sap xep danh sach tang dan theo ten
                               9. Xoa nhan vien theo ma so
                               10. Cap nhat thong tin nhan vien theo ma so
                               11. Thoat menu""");
                System.out.print("Lua chon (nhap so): ");
                Scanner sc = new Scanner(System.in);
                byte choice = sc.nextByte();
                boolean flag, temp;

                do {
                    flag = false;
                    temp = true;
                    switch (choice) {
                        case 1:
                            do {
                                try {
                                    System.out.println("""
                                       \n________________MENU________________
                                       1. Nhap nhan vien kinh doanh (Business)
                                       2. Nhap nhan vien hanh chinh (Administrator)
                                       3. Thoat ra menu chinh""");
                                    System.out.print("Lua chon (nhap so): ");
                                    sc.nextLine();
                                    choice = sc.nextByte();
                                    switch (choice) {
                                        case 1:
                                            Employee newBusiness = new Business();
                                            newBusiness.input(humanResource.employeeList);
                                            humanResource.nhapDS(newBusiness);
                                            break;
                                        case 2:
                                            Employee newAdministrator = new Administrator();
                                            newAdministrator.input(humanResource.employeeList);
                                            humanResource.nhapDS(newAdministrator);
                                            break;
                                        case 3:
                                            temp = false;
                                            break;
                                        default:
                                            System.err.println("Chi nhan ket qua tu 1 den 3");
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.err.println("Nhap sai dinh dang");
                                }
                            } while (temp);
                            break;
                        case 2:
                            humanResource.xuatToanBoDS();
                            break;
                        case 3:
                            do {
                                try {
                                    System.out.println("""
                                       \n________________MENU________________
                                       1. Danh sach nhan vien kinh doanh (Business)
                                       2. Danh sach nhan vien hanh chinh (Administrator)
                                       3. Thoat ra menu chinh""");
                                    System.out.print("Lua chon (nhap so): ");
                                    sc.nextLine();
                                    choice = sc.nextByte();
                                    switch (choice) {
                                        case 1:
                                            humanResource.xuatNVTheoLoai("Business");
                                            break;
                                        case 2:
                                            humanResource.xuatNVTheoLoai("Administrator");
                                            break;
                                        case 3:
                                            temp = false;
                                            break;
                                        default:
                                            System.err.println("Chi nhan ket qua tu 1 den 3");
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.err.println("Nhap sai dinh dang");
                                }
                            } while (temp);
                            break;
                        case 4:
                            humanResource.tinhLuongTB();
                            break;
                        case 5:
                            humanResource.timLuongCaoNhat();
                            break;
                        case 6:
                            humanResource.timLuongThapNhat();
                            break;
                        case 7:
                            System.out.print("Nhap ma so hoac ten nhan vien can tim: ");
                            sc.nextLine();
                            String nameID = sc.nextLine();
                            humanResource.timKiemNV(nameID);
                            break;
                        case 8:
                            humanResource.sapXepNV();
                            break;
                        case 9:
                            System.out.print("Nhap ma so nhan vien: ");
                            sc.nextLine();
                            String maNV = sc.nextLine();
                            humanResource.xoaNVTheoMa(maNV);
                            break;
                        case 10:
                            System.out.print("Nhap ma so nhan vien: ");
                            sc.nextLine();
                            maNV = sc.nextLine();
                            humanResource.capNhatNVTheoMa(maNV);
                            break;
                        case 11:
                            System.out.println("Tam biet\n");
                            System.exit(0);
                            break;
                        default:
                            flag = false;
                            System.err.println("Chi nhan ket qua tu 1 den 11");
                            break;
                    }
                } while (flag);
            } catch (InputMismatchException e) {
                System.err.println("Nhap sai dinh dang");
            }
        } while (true);
    }
}