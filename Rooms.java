
import java.util.Scanner;
import java.util.InputMismatchException;
public class Rooms {
    public final static int number_of_rooms = 200;
    private int room_number;
    private int floor_number;
    private int capacity;
    private double renting_price;
    private boolean occupied ;
    private boolean added ;

    public Rooms() {
        
    }
    public Rooms(int roomNumber, int capacity, double price) {
        this.room_number = roomNumber;
        this.capacity = capacity;
        this.renting_price = price;
        this.occupied = false;
    }
    public Rooms(int room_number, int floor_number,int capacity ,double renting_price) {
        this.room_number = room_number;
        this.floor_number = floor_number;
        this.capacity = capacity;
        this.renting_price = renting_price;
        System.out.println("Room is added successfully.");
    }
    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getCapacity() {
        return capacity;
    }
    // Removed duplicate isOccupied() method

    // Removed duplicate setOccupied method
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public double getRenting_price() {
        return renting_price;
    }

    public void setRenting_price(double renting_price) {
        this.renting_price = renting_price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
    public static void reserve_room (){
        int search_for_capacity = -1; // ay initialization 3lshan java 3ayza
        Scanner input = new Scanner(System.in);
        boolean validinput_capacity = false;
        while (validinput_capacity == false) {
            System.out.println("Enter the capacity of the room you are looking for:");
            try {
                search_for_capacity = input.nextInt();
                //validinput_capacity = true;
                while (search_for_capacity<= 0)
                {
                    System.out.println("Please enter a valid number of persons!");
                    search_for_capacity = input.nextInt();
                }
                validinput_capacity = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number of persons!");
                input.next(); // 3lshan nndf el input
            }
        }
        for (int i = 0; i < number_of_rooms ; i++) {
            if(Main.roomList.get(i).added == true && Main.roomList.get(i).occupied == false){
                if(Main.roomList.get(i).capacity == search_for_capacity){
                    String ans;
                    ans = input.nextLine(); // 3lshan nndf el input w n3ml intialization lel ans
                    boolean validinput_confirm = false;
                    while(validinput_confirm == false){
                        System.out.println("Room found and it costs " + Main.roomList.get(i).renting_price + '$');
                        System.out.println("Do you want to confirm the rent?(Yes/No)");
                        try{
                            ans = input.nextLine();
                            validinput_confirm = true;
                            while (ans.equalsIgnoreCase("Yes") == false && ans.equalsIgnoreCase("No") == false){
                                System.out.println("Invalid input!");
                                System.out.println("Room found and it costs " + Main.roomList.get(i).renting_price + '$');
                                System.out.println("Do you want to confirm the rent?(Yes/No)");
                                ans = input.nextLine();
                            
                            }
                            if (ans.equalsIgnoreCase("yes") == true){
                                System.out.println("Room is reserved successfully and its number is " + Main.roomList.get(i).room_number);
                                Main.roomList.get(i).occupied = true;
                                return;
                            }
                            else if (ans.equalsIgnoreCase("no") == true){
                                System.out.println("Room is NOT reserved successfully.");
                                return;
                            }
                           }
                        catch(InputMismatchException e){
                            System.out.println("Please enter a valid answer.");
                            input.next(); // 3lshan nndf el input
                        }
                    }
        }
    }
}
        System.out.println("There's no such a suitable room!");
}
    public static void display_rooms_info (){
        for (int i = 0; i < number_of_rooms; i++) {
        if (Main.roomList.get(i).added == false) {
            break;
        } else {
            System.out.println("Room number(1-20)      Floor number(1-5)        capacity(persons)      price($)      occupied");
            System.out.print("      " + Main.roomList.get(i).room_number + "                      "
                    + Main.roomList.get(i).floor_number + "                         "
                    + Main.roomList.get(i).capacity + "               "
                    + Main.roomList.get(i).renting_price + "           ");
            System.out.println((Main.roomList.get(i).occupied == true) ? "Yes" : "No");
            System.out.println("===============================================================================================");
        }
    }
    }
   /* private  void initilization (int room_number,int floor_number,int capacity,double renting_price){ // 3mlnaha 3lshan mesh hynf3 n3mlha aw nklm el constructor fe add room 3lshan heya static
        this.room_number=room_number;
        this.floor_number=floor_number;
        this.capacity = capacity;
        this.renting_price = renting_price;
    }*/
    
    public void add_room (){
        Scanner scanner = new Scanner(System.in);
        int room_number=0;
        int floor_number=0;
        double renting_price=0;
        int capacity=0;
        boolean validinput_rn=false;
        boolean validinput_fn=false;
        boolean validinput_rp=false;
        boolean validinput_capacity=false;
        
        while(validinput_rn == false){
            System.out.println("Enter the room number: ");
            try{
                room_number = scanner.nextInt();
                for (int i = 0; i < number_of_rooms; i++) {
                    for (int j = 0; j < number_of_rooms; j++) {
                        while (room_number < 1 || room_number > 20) {
                            System.out.println("Invalid room number!\nPlease enter a number between (1-20).");
                            room_number = scanner.nextInt();
                        }
                        while (room_number == Main.roomList.get(j).room_number) {
                            System.out.println("This room number is already taken. Please enter another number.");
                            room_number = scanner.nextInt();
                        }
                    }
                }
                validinput_rn=true;
            } 
            catch(InputMismatchException e){
                System.out.println("Please enter a valid room number!");
                scanner.next(); // 3lshan nndf el input
            } 
        }
        while(validinput_fn == false){
        System.out.print("Enter the floor number: ");
        try{
            floor_number = scanner.nextInt();
            int check = 0;
            
            for (int k = 0; k < number_of_rooms; k++) {
                while (floor_number < 1 || floor_number > 5) {
                    System.out.println("Invalid floor number!\nPlease enter a number between (1-5).");
                    floor_number = scanner.nextInt();
                }

                if (Main.roomList.get(k).floor_number == floor_number) {
                    check++;
                }

                if (check >= 2) {
                    System.out.println("Sorry, a floor can only have at most 2 rooms!\nPlease enter another floor number.");
                    floor_number = scanner.nextInt();
                    check = 0;
                }
            }
            validinput_fn = true;
        }
        catch (InputMismatchException e){
            System.out.println("Please enter a valid floor number!");
            scanner.next(); // 3lshan nndf el input
            }
        }
        while (validinput_rp == false){
        System.out.print("Enter the room price: ");
        try{
            renting_price = scanner.nextDouble();

            while (renting_price < 0) {
                System.out.println("Please enter a valid price!");
                renting_price = scanner.nextDouble();
            }
            validinput_rp = true;
        }
        catch(InputMismatchException e){
            System.out.println("Please enter a valid price!");
            scanner.next(); // 3lshan nndf el input
        }
        }
        while(validinput_capacity == false){
            System.out.print("Enter the room capacity: ");
            try{
                capacity = scanner.nextInt();

                while (capacity <= 0) {
                    System.out.println("Please enter a valid number of persons!");
                    capacity = scanner.nextInt();
                }
                validinput_capacity = true;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a valid price!");
                scanner.next(); // 3lshan nndf el input
            }
    }
       // Rooms room = new Room();
        /*for (int q = 0; q < number_of_rooms; q++) {
            if(main.roomslist.get(q).added == true){
                continue;
            }                                             fel main ht3ml object w htnady 3la el function de b3dha tb3t el object da lel arraylist bs htb3to zy ma m3mool fel for loop de
            else{
                main.roomslist.add(q,room);
            }
        }*/
       this.room_number=room_number;
        this.floor_number=floor_number;
        this.capacity = capacity;
        this.renting_price = renting_price;
        this.added = true;
        System.out.println("Room is added successfully.");
    }
    
}