public class mainold {
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Ensure the Gender enum is imported or defined correctly
// Define the Gender enum directly if it is missing
enum Gender {
    MALE, FEMALE, OTHER;
}

    static List<Admin> adminList = new ArrayList<>();
    static List<Category> categoryList = new ArrayList<>();
    static List<finalAtendee> attendeeList = new ArrayList<>();
    static List<Organizer> organizerList = new ArrayList<>();
    static List<Event> eventList = new ArrayList<>(); 
    static List<Rooms> roomList = new ArrayList<>(); 
    public static void main(String[] args) {
       
        Rooms room = new Rooms();
        for (int l = 0; l < Rooms.number_of_rooms; l++) {
            roomList.add(room);
        }
        for (int h = 0; h < Rooms.number_of_rooms; h++) {
            roomList.get(h).setAdded(false);
        }
    
        Scanner scanner = new Scanner(System.in);
       
        boolean running = true; // Flag to keep the program running

        while (running) {
            System.out.println("Welcome to the Event Management System");
            System.out.println("1. Admin");
            System.out.println("2. Organizer");
            System.out.println("3. Attendee");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1. Admin Login");
                    System.out.println("2. Admin Register");
                    System.out.print("Enter your choice: ");
                    int adminChoice = scanner.nextInt();

                    switch (adminChoice) {
                        case 1: // Admin Login
                            System.out.print("Enter username: ");
                            String loginUsername = scanner.next();
                            System.out.print("Enter password: ");
                            String loginPassword = scanner.next();

                            boolean loginSuccess = false;
                            for (Admin admin : adminList) {
                                if (admin.getUsername().equals(loginUsername) && admin.getPassword().equals(loginPassword)) {
                                    System.out.println("Login successful!");
                                    loginSuccess = true;

                                    // Admin functionalities
                                    boolean adminRunning = true;
                                    while (adminRunning) {
                                        System.out.println("Welcome, " + loginUsername + "!");
                                        System.out.println("1. Create Category");
                                        System.out.println("2. View Categories");
                                        System.out.println("3. Update Category");
                                        System.out.println("4. Delete Category");
                                        System.out.println("5. Create Room");
                                        System.out.println("6. View Events");
                                        System.out.println("7. View Attendees");
                                        System.out.println("8.View Rooms");
                                        System.out.println("9. Logout");
                                        System.out.print("Enter your choice: ");
                                        int adminFunctionChoice = scanner.nextInt();

                                        switch (adminFunctionChoice) {
                                            case 1: // Create Category
                                                System.out.print("Enter category name: ");
                                                String categoryName = scanner.next();
                                                System.out.print("Enter category description: ");
                                                scanner.nextLine(); // Consume newline
                                                String categoryDescription = scanner.nextLine();
                                                admin.createCategory(categoryList, categoryName, categoryDescription);
                                                break;

                                            case 2: // View Categories
                                                admin.viewCategories(categoryList);
                                                break;

                                            case 3: // Update Category
                                                System.out.print("Enter old category name: ");
                                                String oldName = scanner.next();
                                                System.out.print("Enter new category name: ");
                                                String newName = scanner.next();
                                                System.out.print("Enter new category description: ");
                                                String newDescription = scanner.next();

                                             admin.updateCategory(categoryList, oldName, newName, newDescription);
                                                break;

                                            case 4: // Delete Category
                                                System.out.print("Enter category name to delete: ");
                                                String deleteName = scanner.next();
                                                
                                                admin.deleteCategory(categoryList, deleteName);
                                               
                                                
                                                break;
                                                case 5: // Create Room
                                                for (int q = 0; q < Rooms.number_of_rooms; q++) {
                                                    if (roomList.get(q).isAdded() == true) {
                                                        continue;
                                                    }else{
                                                        roomList.get(q).add_room();
                                                        break;
                                                    }
                                                }
                                                break;
                                            
                                                case 6: // View Events
                                                if (eventList.isEmpty()) {
                                                    System.out.println("No events in the system.");
                                                } else {
                                                    System.out.println("Events in the system:");
                                                    for (Event event : eventList) {
                                                        System.out.println(event); // Assuming Event class has a proper toString() method
                                                    }
                                                }
                                                break; 
                                            case 7: //view attendees
                                                System.out.println("Attendees in the system:");
                                                for (finalAtendee attendee : attendeeList) {
                                                    System.out.println(attendee); // Assuming finalAtendee class has a proper toString() method
                                                }
                                                break;    

                                             
                                            case 8: // View Rooms
                                            Rooms.display_rooms_info();

                                            case 9: // Logout
                                                adminRunning = false;
                                                System.out.println("Logged out successfully.");
                                                break;

                                            default:
                                                System.out.println("Invalid choice. Please try again.");
                                                break;
                                        }
                                    }
                                    break;
                                }
                            }
                            if (!loginSuccess) {
                                System.out.println("Invalid credentials. Please try again.");
                            }
                            break;

                        case 2: // Admin Register
                            System.out.print("Enter username: ");
                            String regUsername = scanner.next();
                            System.out.print("Enter password: ");
                            String regPassword = scanner.next();
                            System.out.print("Enter date of birth (YYYY-MM-DD): ");
                            String dob = scanner.next();
                            System.out.print("Enter role: ");
                            String role = scanner.next();
                            System.out.print("Enter working hours: ");
                            String workingHours = scanner.next();

                            boolean usernameExists = false;
                            for (Admin admin : adminList) {
                                if (admin.getUsername().equals(regUsername)) {
                                    usernameExists = true;
                                    break;
                                }
                            }

                            if (usernameExists) {
                                System.out.println("Username already exists. Please choose a different username.");
                            } else {
                                Admin newAdmin = new Admin(regUsername, regPassword, dob, role, workingHours);
                                adminList.add(newAdmin);
                                System.out.println("Admin registered successfully!");
                            }
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
            case 2:
                System.out.println("1.Organizer Login");
                System.out.println("2.Organizer Register");
                System.out.print("Enter your choice: ");
                // Organizer login here
                int organizerChoice = scanner.nextInt();
                switch (organizerChoice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String organizerUsername = scanner.next();
                        System.out.print("Enter password: ");
                        String organizerPassword = scanner.next();
                        boolean organizerLoginSuccess = false;
                        while (!organizerLoginSuccess) {
                            for (Organizer organizer : organizerList) {
                                if (organizer.getUsername().equals(organizerUsername) && organizer.getPassword().equals(organizerPassword)) {
                                    System.out.println("Login successful!");
                                    organizerLoginSuccess = true;

                                    // Organizer functionalities
                                    boolean organizerRunning = true;
                                    while (organizerRunning) {
                                        System.out.println("Welcome, " + organizerUsername + "!");
                                        System.out.println("1. Create Event");
                                        System.out.println("2. View Events");
                                        System.out.println("3. Update Event");
                                        System.out.println("4. Delete Event");
                                        System.out.println("5. create room");
                                        System.out.println("6. Logout");
                                        System.out.print("Enter your choice: ");
                                        int organizerFunctionChoice = scanner.nextInt();

                                        switch (organizerFunctionChoice) {
                                            case 1: // Create Event
                                            if (categoryList.isEmpty()) {
                                                System.out.println("No categories available. Please ask the admin to create categories first.");
                                                break;
                                            }
                                        
                                            System.out.println("Available Categories:");
                                            for (int i = 0; i < categoryList.size(); i++) {
                                                System.out.println((i + 1) + ". " + categoryList.get(i).getName());
                                            }
                                        
                                            System.out.print("Select a category by entering its number: ");
                                            int categoryChoice = scanner.nextInt();
                                            scanner.nextLine(); // Consume newline
                                        
                                            if (categoryChoice < 1 || categoryChoice > categoryList.size()) {
                                                System.out.println("Invalid category choice. Please try again.");
                                                break;
                                            }
                                        
                                            Category selectedCategory = categoryList.get(categoryChoice - 1);
                                        
                                            // Create a new event
                                            Event newEvent = new Event();
                                            newEvent.create(); // Assuming the `create` method populates the event details
                                        
                                            // Add the event to the selected category
                                            selectedCategory.addevent(newEvent);
                                        
                                            // Add the event to the global event list
                                            eventList.add(newEvent);
                                        
                                            System.out.println("Event added to category: " + selectedCategory.getName());
                                            break;
                                            case 2: // View Events
                                                // Event viewing logic here
                                                System.out.println("Events created by you:");
                                                for (Event event : organizer.readevents()) {
                                                    System.out.println(event); // Assuming Event class has a proper toString() method
                                                }
                                                System.out.println("Events displayed successfully.");
                                           
                                                break;

                                            case 3: // Update Event
                                                // Event updating logic here
                                                System.out.print("Enter the event name to update: ");
                                                scanner.nextLine(); // Consume newline
                                                String eventNameToUpdate = scanner.nextLine();

                                                boolean eventFound = false;
                                                for (int i = 0; i < organizer.readevents().size(); i++) {
                                                    if (organizer.readevents().get(i).getEventName().equals(eventNameToUpdate)) {
                                                        System.out.println("Updating event: " + eventNameToUpdate);
                                                        organizer.readevents().get(i).create(); // Reuse the `create` method to update the event details
                                                        eventFound = true;
                                                        break;
                                                    }
                                                }

                                                if (!eventFound) {
                                                    System.out.println("Event not found.");
                                                }
                                                break;

                                            case 4: // Delete Event
                                                // Event deletion logic here
                                                System.out.print("Enter the event name to delete: ");
                                                scanner.nextLine(); // Consume newline
                                                String eventNameToDelete = scanner.nextLine();
                                                boolean eventDeleted = false;
                                                for (int i = 0; i < organizer.readevents().size(); i++) {
                                                    if (organizer.readevents().get(i).getEventName().equals(eventNameToDelete)) {
                                                        organizer.del_event(i); // Delete the event from the organizer's list
                                                        eventDeleted = true;
                                                        System.out.println("Event deleted successfully.");
                                                        break;
                                                    }
                                                }

                                                case 5: // Create Room
                                                for (int q = 0; q < Rooms.number_of_rooms; q++) {
                                                    if (roomList.get(q).isAdded() == true) {
                                                        continue;
                                                    }else{
                                                        roomList.get(q).add_room();
                                                        break;
                                                    }
                                                }
                                                break;

                                                case 6: // Logout
                                                organizerRunning = false; // Exit the organizer session
                                                System.out.println("Logged out successfully.");
                                                break;

                                            default:
                                                System.out.println("Invalid choice. Please try again.");
                                                break;
                                        }
                                    }
                                    break;
                                }
                            }

                            if (!organizerLoginSuccess) {
                                System.out.println("Invalid credentials. Please try again.");
                                System.out.print("Do you want to try again? (yes/no): ");
                                String retryChoice = scanner.next();
                                if (retryChoice.equalsIgnoreCase("no")) {
                                    break; // Exit the login loop
                                }
                            }
                        }
                        break; // Add this break to prevent falling into the next case

                    case 2:
                     // Organizer Register
                     // Registration logic here
                        System.out.print("Enter username: ");
                        String orgUsername = scanner.next();
                        System.out.print("Enter password: ");
                        String orgPassword = scanner.next();
                        Organizer newOrganizer = new Organizer(orgUsername, orgPassword); // Create a new organizer object
                        organizerList.add(newOrganizer); // Add the new organizer to the list
                        System.out.println("Organizer registered successfully!");

                     break;
                    default:
                     System.out.println("Invalid choice. Please try again.");
                     break;
                }
                
                
                break;
            case 3:
                System.out.println("1. Attendee Login");
                System.out.println("2. Attendee Register");
                System.out.print("Enter your choice: ");
                int attendeeChoice = scanner.nextInt();
                switch (attendeeChoice) {
                    case 1:
                    boolean attendeeLoginSuccess = false;
                    while (!attendeeLoginSuccess) {
                        System.out.print("Enter username: ");
                        String attendeeUsername = scanner.next();
                        System.out.print("Enter password: ");
                        String attendeePassword = scanner.next();
                
                        for (finalAtendee attendee : attendeeList) {
                            if (attendee.getUsername().equals(attendeeUsername) && attendee.getPassword().equals(attendeePassword)) {
                                System.out.println("Login successful!");
                                attendeeLoginSuccess = true;
                
                                // Attendee functionalities
                                boolean attendeeRunning = true;
                                while (attendeeRunning) {
                                    System.out.println("Welcome, " + attendeeUsername + "!");
                                    System.out.println("1. View Profile");
                                    System.out.println("2. view and buy tickets");
                                    System.out.println("3. Logout");
                                    System.out.print("Enter your choice: ");
                                    int attendeeFunctionChoice = scanner.nextInt();
                
                                    switch (attendeeFunctionChoice) {
                                        case 1: // View Profile
                                            for (finalAtendee attendeeObj : attendeeList) {
                                                if (attendeeObj.getUsername().equals(attendeeUsername)) {
                                                    attendeeObj.displayProfile();
                                                    break;
                                                }
                                            }
                                            System.out.println("Profile displayed successfully.");
                                            break;
                
                                        case 2: // Update Profile
                                        if (eventList.isEmpty()) {
                                            System.out.println("No events available.");
                                            break;
                                        }
                                    
                                        System.out.println("Available Events:");
                                        for (int i = 0; i < eventList.size(); i++) {
                                            System.out.println((i + 1) + ". " + eventList.get(i)); // Assuming Event class has a proper toString() method
                                        }
                                    
                                        System.out.print("Enter the number of the event you want to buy a ticket for: ");
                                        int eventChoice = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                    
                                        if (eventChoice < 1 || eventChoice > eventList.size()) {
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                        }
                                    
                                        Event selectedEvent = eventList.get(eventChoice - 1);
                                        System.out.println("Selected Event: " + selectedEvent);
                                    
                                        // Check if the attendee has enough balance
                                        System.out.println("Ticket price: " + selectedEvent.getPrice() + " L.E");
                                        finalAtendee currentAttendee = null;
                                        for (finalAtendee a : attendeeList) {
                                            if (a.getUsername().equals(attendeeUsername)) {
                                                currentAttendee = a;
                                                break;
                                            }
                                        }
                                        
                                        if (attendee != null && attendee.get_balance() >= selectedEvent.getPrice()) {
                                            System.out.print("Do you want to confirm the purchase? (yes/no): ");
                                            String confirm = scanner.nextLine();
                                    
                                            if (confirm.equalsIgnoreCase("yes")) {
                                                attendee.dec_balance(selectedEvent.getPrice()); // Deduct the ticket price from the attendee's wallet
                                    
                                                // Find the organizer who created the event and increase their balance
                                                for (Organizer organizer : organizerList) {
                                                    if (organizer.readevents().contains(selectedEvent)) {
                                                        organizer.get_wallet().inc_balance(selectedEvent.getPrice());
                                                        break;
                                                    }
                                                }
                                    
                                                System.out.println("Ticket purchased successfully!");
                                            } else {
                                                System.out.println("Purchase canceled.");
                                            }
                                        } else {
                                            System.out.println("Insufficient balance to buy this ticket.");
                                        }
                                        break;
                
                                        case 3: // Logout
                                            attendeeRunning = false;
                                            System.out.println("Logged out successfully.");
                                            break;
                
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                }
                                break;
                            }
                        }
                
                        if (!attendeeLoginSuccess) {
                            System.out.println("Invalid credentials. Please try again.");
                            System.out.println("Do you want to try again? (yes/no): ");
                            String retryChoice = scanner.next();
                            if (retryChoice.equalsIgnoreCase("no")) {
                                break; // Exit the login loop
                            }
                        }
                    }
                    break; // Add this break to prevent falling into the next case
                    case 2:
                     // Attendee Register
                     finalAtendee newAttendee = new finalAtendee(); // Create a new attendee object
                    newAttendee.createAccount(newAttendee); // Populate the attendee details
                    attendeeList.add(newAttendee); // Add the new attendee to the attendeeList
                    System.out.println("Attendee registered successfully!");
                    default:
                    
                }
                // Attendee login here
                break;
            case 4:
                System.out.println("Exiting the system. Goodbye!");
                running = false; // Exit the loop
                break;
            default:
                System.out.println("Invalid choice. Please try again.");














        }
    }}}




