import java.util.*;
import java.io.*;

public class MyList {
    Node head, tail;
    int size;

    MyList() {
        this.head = null;
        this.tail=null;
        this.size = 0;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while(p != null) {
            f.writeBytes(p.getInfo() + " "); // write data in the node p to the file f
            p = p.next;
        }
        
        f.writeBytes("\r\n");
    }
    
    void loadData(int k) {
        String [] a = Lib.readLineToStrArray("data.txt", k);
        int [] b = Lib.readLineToIntArray("data.txt", k+1);
        int [] c = Lib.readLineToIntArray("data.txt", k+2);
        int n = a.length;
        
        for(int i = 0; i < n; i++) 
            addLast(a[i],b[i],c[i]);// insert the new Node(a[i], b[i], c[i]) into the list
    }
     
    void addLast(String xDistrict, int xPrice, int xArea) {
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Apartment x = new Apartment(xDistrict, xPrice, xArea);
        if(isEmpty())
            head=tail=new Node(x,null);
        else {
            Node q =new Node(x,null);
            tail.next=q; 
            tail=q;
            size++;
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
    }
    
	// f1: ham nay se goi ham addLast nhieu lan
    void f1() throws Exception {
        clear();
        loadData(0);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 
        ftraverse(f);
        f.close();
    }
    
	// f2: ham addFirst ==> du lieu nhap tu ban phim
    void f2() throws Exception {
        clear();
        loadData(0);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        String xDistrict;
        int xPrice, xArea;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter dítrict: ");
        xDistrict = sc.nextLine();
        System.out.println("Enter price: ");
        xPrice = sc.nextInt();
        System.out.println("Enter area: ");
        xArea = sc.nextInt();
        Apartment x = new Apartment(xDistrict, xPrice, xArea);
        Node node = new Node(x,head);
        if (isEmpty())
            head=tail=node;
        else head = node;
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
    // f3: ham addPos ==> them node vao vi tri thu k, trong do node moi va chi so k duoc nhap tu ban phim
    void f3() throws Exception {
        clear();
        loadData(0);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        int index;
        String xDistrict;
        int xPrice, xArea;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter dítrict: ");
        xDistrict = sc.nextLine();
        System.out.println("Enter price: ");
        xPrice = sc.nextInt();
        System.out.println("Enter area: ");
        xArea = sc.nextInt();
        System.out.println("Enter index: ");
        index = sc.nextInt();
        Apartment x = new Apartment(xDistrict, xPrice, xArea);
        if (index < 0) return;
        if (index == 0) addLast(xDistrict, xPrice, xArea);
        else{
            Node current = head;
            int pos = 0;
            while (current != null){
                if (index-1 == pos) break;
                current = current.next;
                pos++;
            }
            if (current == null) return;
            else {
                Node node = new Node (x,current.next);
                current.next = node;
                size++;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
	// f4: removeFirst
    void f4() throws Exception {
        clear();
        loadData(0);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (head==tail)
            head=tail=null;
        else head=head.next;
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
	
	// f5: removeLast
	 void f5() throws Exception {
        clear();
        loadData(0);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if (head==tail)
            head=tail=null;
        else {
            Node p = head;
            while (p.next != tail){
                p=p.next;
            }
            p.next = null;
            tail = p;
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
    // f6: filter ==> chi giu lai cac node theo mot yeu cau cho truoc (yeu cau tu chon)
    void f6() throws Exception {
        clear();
        loadData(0);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if(isEmpty()){
            System.out.println("List is empty");
        } else {
            Node p = head;
            System.out.println("Node with area>3:");
            while(p != null){
                if(p.getInfo().area>3){
                    System.out.println(p.getInfo());
                }
                p = p.next;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
	// f7: dem so node theo dieu kien (tu thiet ke dieu kien)
    void f7() throws Exception {
        clear();
        loadData(0);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if(isEmpty()){
            System.out.println("List is empty");
        } else {
            int count = 0;
            Node p = head;
            while(p != null){
                if(p.getInfo().price<6){
                    System.out.println(p.getInfo());
                    count++;
                }
                p = p.next;
            }
            System.out.println("Number of node with price<6: " + count);
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
	
	// f8: dao nguoc list
	 void f8() throws Exception {
        clear();
        loadData(0);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        Node current = head, next = null, prev = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        tail = head;
        head = prev;
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
    // f9: update thong tin theo yeu cau (tu thiet ke yeu cau)
    void f9() throws Exception {
        clear();
        loadData(0);
        String fname = "f9.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        String xDistrict;
        int xPrice, xArea, position, count = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Enter update index: ");
            position = Integer.parseInt(sc.nextLine().trim());
            if(position>=size)
                System.out.println("Out of range");
        }while(position >=size);
       
        Node q = head;
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            while (q != null) {
                if(count==position){
                    System.out.println("Enter district:");
                    xDistrict = sc.nextLine();
                    System.out.println("Enter price:");
                    xPrice = sc.nextInt();
                    System.out.println("Enter area:");
                    xArea = sc.nextInt();
                    q.getInfo().setDistrict(xDistrict);
                    q.getInfo().setPrice(xPrice);
                    q.getInfo().setArea(xArea);

                }
                count++;
                q = q.next;
            }
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    
	// f10: search ==> tu thiet ke dieu kien search
    void f10() throws Exception {
        clear();
        loadData(0);
        String fname = "f10.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile f = new RandomAccessFile(fname, "rw"); 

        //------------------------------------------------------------------------------------
        //------ Start your code here---------------------------------------------------------
        if(isEmpty()){
            System.out.println("List is empty");
        } else {
            Node p = head;
            while(p != null){
                if(p.getInfo().district.equals("Q1")){
                    System.out.println(p.getInfo());
                }
                p = p.next;
            }       
        }
        //------ End your code here-----------------------------------------------------------
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
  
}

