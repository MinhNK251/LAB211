import java.util.Scanner;
public class TrafficLight {
    private String color;
    private int time;
    public TrafficLight(String color, int time) {
        this.color = color;
        this.time = time;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void changeColor(String newColor) {
        this.color = newColor;
    }
    public void checkColor(String color) {
        if(color.equalsIgnoreCase("red"))
            System.out.println("Red");
        else if(color.equalsIgnoreCase("blue"))
            System.out.println("Blue");
        else System.out.println("Neither red nor blue");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter color of traffic light: ");
        String color = sc.nextLine();
        System.out.print("Enter time of traffic light: ");
        int time = sc.nextInt();
        TrafficLight light = new TrafficLight(color,time);
        System.out.print("Check color of traffic light : ");
        light.checkColor(light.getColor().toLowerCase());
        System.out.print("Change traffic light color: ");
        sc.nextLine();
        light.changeColor(sc.nextLine());
        System.out.println("Color of traffic light : "+light.getColor());
    }
}