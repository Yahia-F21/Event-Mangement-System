/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author rightway
 */

import java.util.Objects;
import java .util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

public class Event {
    Scanner in=new Scanner(System.in);
    
   private String eventName;
   private String eventDate;
    private String eventTime;
    private String eventLocation;
    private double eventPrice;
   private int room_Num;
    private int day;
    private int month;
    private String hour;
    private double price;
    private List<finalAtendee> purchasedBy = new ArrayList<>();
      public Event(String eventName, String eventDate, String eventTime, double eventPrice, int roomNum) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventPrice = eventPrice;
        this.room_Num = roomNum;
    }

    public Event(String eventName2, String eventDate, int room_Num, String eventprice, String eventTime) {
        this.eventName = eventName2;
       this.eventDate = eventDate;
        this.room_Num = room_Num;
        this.hour = eventTime;
        this.price = Double.parseDouble(eventprice); // Assuming eventprice is a valid numeric string
    }
    public Event() {
        // Default constructor
    }
    public String getEventName() {
        return eventName;
    }
    public void addPurchaser(finalAtendee attendee) {
        purchasedBy.add(attendee);
    }

    public boolean isPurchasedBy(finalAtendee attendee) {
        return purchasedBy.contains(attendee);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    
    public int getRoom_Num() {
        return room_Num;
    }
   
    public void setRoom_Num(int room_Num) {
        while(true){
        if(room_Num<1||room_Num>200){
            System.out.println("unavalable num,try again");
            room_Num=in.nextInt();
        }
        else{
        this.room_Num = room_Num;
        break;}
        
    }}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        while(true){
        int[]monthDays={0,31,28,31,30,31,30,31,31,30,31,30,31};
        if(day<1||day>monthDays[this.getMonth()]){
       
            System.out.println("ivalid day");
            System.out.println("enter the day");
            day=in.nextInt();}
            
   
        else{
        this.day = day;
        break;} 
    }}

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        while(true){
           
        if(month>12||month<1){
            System.out.println("invalid month");
            System.out.println("reenter the month");
           // this.setMonth(in.nextInt());
           month=in.nextInt();
        }
        else{
        this.month = month;
        break;}
        
    }}
    


    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
       
         while (true) {
        String input = hour.trim().toUpperCase();
        if (input.matches("^(0?[1-9]|1[0-2]):[0-5][0-9] (AM|PM)$")) {
            this.hour = input;
            break;
        } else {
            System.out.println("Invalid time format. Use h:mm AM/PM (e.g., 2:30 PM). Try again:");
            hour=in.nextLine();
        }
    }
}
   public void setRoomNum(int roomNum) {
        this.room_Num = roomNum;
    }
   

    // Getter for roomNum (optional, if needed)
    public int getRoomNum() {
        return room_Num;
    }
    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public double getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(double eventPrice) {
        this.eventPrice = eventPrice;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   
 
    public void create(){
        boolean valid=false;
        
        System.out.println("ENTER EVENT NAME");
        while(!valid){
            try{
         this.setEventName(in.nextLine());
         valid=true;}
            catch(InputMismatchException ex){
                System.out.println("invalid name");
                in.next();
            }}
        System.out.println("enter room number");
        do{
            try{
        this.setRoom_Num(in.nextInt());
        valid=false;}
            catch(InputMismatchException ex){
            System.out.println("invalid input");
            in.next();}
        }while(valid);
        System.out.println("enter the month");
       
        
      while(!valid)  {
       try{ 
           
        this.setMonth(in.nextInt());
       valid=true;
       }
       catch(InputMismatchException ex){
            System.out.println("invalid ,try again");
            in.next();
        }}
        
         System.out.println("enter day");
        while(valid){
        try{    
        this.setDay(in.nextInt());
       valid=false;}
        catch(InputMismatchException ex){
            System.out.println("invalid,try again");
            in.next();}
        }
        
        System.out.println("enter time , Use h:mm AM/PM (e.g., 2:30 PM)");
        in.nextLine();
        this.setHour(in.nextLine());
        System.out.println("enter price");
        while(!valid){
            try{
        this.setPrice(in.nextDouble());
        valid= true;}
            catch(InputMismatchException ex){
                System.out.println("invalid , try again");
                in.next();
            }}
        
      
       
        
    
    
    
    }

    public void update(Organizer organizer) {
        System.out.println("Enter the event name that you want to update:");
        String n = in.nextLine();
        boolean found = false;
    
        for (int i = 0; i < organizer.readevents().size(); i++) {
            if (n.equals(organizer.readevents().get(i).getEventName())) {
                System.out.println("Updating event: " + n);
                organizer.readevents().get(i).create(); // Reuse the `create` method to update the event details
                found = true;
                break;
            }
        }
    
        if (!found) {
            System.out.println("Event not found.");
        }
    }

    @Override
     public String toString() {
        return "Event Name: " + eventName +
               ", Date: " + eventDate +
               ", Time: " + eventTime +
               ", Price: $" + eventPrice +
               ", Room: " + room_Num;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        final Event other = (Event) obj;
       
        return this.room_Num==other.room_Num && this.day==other.day&&this.month==other.month;
    }
  
}
