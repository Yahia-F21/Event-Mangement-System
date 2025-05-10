import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    static List<Admin> adminList = new ArrayList<>();
    static List<Category> categoryList = new ArrayList<>();
    static List<finalAtendee> attendeeList = new ArrayList<>();
    static List<Organizer> organizerList = new ArrayList<>();
    static List<Event> eventList = new ArrayList<>(); 
    static List<Rooms> roomList = new ArrayList<>(); 
    static finalAtendee loggedInAttendee = null; 
    @Override
    public void start(Stage primaryStage) {
        
        VBox mainMenu = new VBox(10);
        mainMenu.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color:rgb(78, 192, 210);");

        Label welcomeLabel = new Label("Welcome to the Event Management System");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:rgb(29, 57, 86);");
        Button adminButton = new Button("Admin");
        Button organizerButton = new Button("Organizer");
        Button attendeeButton = new Button("Attendee");
        Button exitButton = new Button("Exit");

        mainMenu.getChildren().addAll(welcomeLabel, adminButton, organizerButton, attendeeButton, exitButton);

        
        Scene mainScene = new Scene(mainMenu, 400, 300);

        // Admin Menu
        adminButton.setOnAction(e -> showLoginRegisterWindow(primaryStage, mainScene, "Admin"));

        // Organizer Menu
        organizerButton.setOnAction(e -> showLoginRegisterWindow(primaryStage, mainScene, "Organizer"));

        // Attendee Menu
        attendeeButton.setOnAction(e -> showLoginRegisterWindow(primaryStage, mainScene, "Attendee"));

        // Exit Button
        exitButton.setOnAction(e -> primaryStage.close());

        
        primaryStage.setTitle("Event Management System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void showLoginRegisterWindow(Stage primaryStage, Scene mainScene, String userType) {
        VBox loginRegisterMenu = new VBox(10);
        loginRegisterMenu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label loginRegisterLabel = new Label("Login or Register as " + userType);
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        loginRegisterMenu.getChildren().addAll(loginRegisterLabel, loginButton, registerButton, backButton);

        Scene loginRegisterScene = new Scene(loginRegisterMenu, 400, 300);

        // Login Button
        loginButton.setOnAction(e -> showLoginWindow(primaryStage, loginRegisterScene, userType));

        // Register Button
        registerButton.setOnAction(e -> showRegisterWindow(primaryStage, loginRegisterScene, userType));

        // Back Button
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));

        primaryStage.setScene(loginRegisterScene);
    }

    private void showLoginWindow(Stage primaryStage, Scene previousScene, String userType) {
        VBox loginMenu = new VBox(10);
        loginMenu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label loginLabel = new Label("Login as " + userType);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginSubmitButton = new Button("Login");
        Button backButton = new Button("Back");

        loginMenu.getChildren().addAll(loginLabel, usernameField, passwordField, loginSubmitButton, backButton);

        Scene loginScene = new Scene(loginMenu, 400, 300);

        
        loginSubmitButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            
            if (validateLogin(username, password, userType)) {
                System.out.println(userType + " logged in successfully!");
                switch (userType) {
                    case "Admin":
                        showAdminMenu(primaryStage, previousScene);
                        break;
                    case "Organizer":
                        showOrganizerMenu(primaryStage, previousScene);
                        break;
                    case "Attendee":
                        showAttendeeMenu(primaryStage, previousScene);
                        break;
                }
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        });

        
        backButton.setOnAction(e -> primaryStage.setScene(previousScene));

        primaryStage.setScene(loginScene);
    }

    private void showRegisterWindow(Stage primaryStage, Scene previousScene, String userType) {
        VBox registerMenu = new VBox(10);
        registerMenu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label registerLabel = new Label("Register as " + userType);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button registerSubmitButton = new Button("Register");
        Button backButton = new Button("Back");

        registerMenu.getChildren().addAll(registerLabel, usernameField, passwordField, registerSubmitButton, backButton);

        Scene registerScene = new Scene(registerMenu, 400, 300);

        
        registerSubmitButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            
            if (registerUser(username, password, userType)) {
                System.out.println(userType + " registered successfully!");
                primaryStage.setScene(previousScene);
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        });

        
        backButton.setOnAction(e -> primaryStage.setScene(previousScene));

        primaryStage.setScene(registerScene);
    }

    private void showAdminMenu(Stage primaryStage, Scene mainScene) {
        VBox adminMenu = new VBox(10);
        adminMenu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label adminLabel = new Label("Admin Menu");
        Button createCategoryButton = new Button("Create Category");
        Button viewCategoriesButton = new Button("View Categories");
        Button updateCategoriesButton = new Button("Update Categories");
        Button deleteCategoriesButton = new Button("Delete Categories");
        Button viewEventsButton = new Button("View Events");
        Button showattendeeButton = new Button("Show Attendees");
        Button createroomButton = new Button("Create Room");
        Button viewroomButton = new Button("View Rooms");
        Button logoutButton = new Button("Logout");

        adminMenu.getChildren().addAll(adminLabel, createCategoryButton, viewCategoriesButton, updateCategoriesButton,deleteCategoriesButton ,viewEventsButton,showattendeeButton,createroomButton,viewroomButton,logoutButton);

        Scene adminScene = new Scene(adminMenu, 400, 300);

        // Create Category Button
        createCategoryButton.setOnAction(e -> showCreateCategoryDialog());

        // View Categories Button
        viewCategoriesButton.setOnAction(e -> showCategories());
        // update categories
        updateCategoriesButton.setOnAction(e -> updateCategories());
        // delete categories
        deleteCategoriesButton.setOnAction(e -> deleteCategories());
        //view events
        viewEventsButton.setOnAction(e -> showEvents());
        //show attendees
        showattendeeButton.setOnAction(e -> showattendees());
        // create room
        createroomButton.setOnAction(e -> showCreateRoomDialog());
        // view room
        viewroomButton.setOnAction(e -> showRooms());
        // Logout Button
        logoutButton.setOnAction(e -> primaryStage.setScene(mainScene));

        primaryStage.setScene(adminScene);
    }

    private void showOrganizerMenu(Stage primaryStage, Scene mainScene) {
        VBox organizerMenu = new VBox(10);
        organizerMenu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label organizerLabel = new Label("Organizer Menu");
        Button createEventButton = new Button("Create Event");
        Button viewEventsButton = new Button("View Events");
        Button editeventButton = new Button("Edit Event");
        Button deleteeventButton = new Button("Delete Event");
        Button showroomButton = new Button("Show Rooms");
        Button logoutButton = new Button("Logout");

        organizerMenu.getChildren().addAll(organizerLabel, createEventButton, viewEventsButton,editeventButton , deleteeventButton,showroomButton,logoutButton);

        Scene organizerScene = new Scene(organizerMenu, 400, 300);

        // Create Event Button
        createEventButton.setOnAction(e -> Createvents());

        // View Events Button
        viewEventsButton.setOnAction(e -> showEvents());
        // Edit Event Button
        editeventButton.setOnAction(e -> editevent());
        // Delete Event Button
        deleteeventButton.setOnAction(e -> deleteevent());
        // show room
        showroomButton.setOnAction(e -> showRooms());
        // Logout Button
        logoutButton.setOnAction(e -> primaryStage.setScene(mainScene));

        primaryStage.setScene(organizerScene);
    }

    private void showAttendeeMenu(Stage primaryStage, Scene mainScene) {
        VBox attendeeMenu = new VBox(10);
        attendeeMenu.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label attendeeLabel = new Label("Attendee Menu");
        Button viewProfileButton = new Button("View Profile");
        Button buyTicketsButton = new Button("Buy Tickets");
        Button viewEventsButton = new Button("View Events");
        Button logoutButton = new Button("Logout");

        attendeeMenu.getChildren().addAll(attendeeLabel, viewProfileButton, buyTicketsButton,viewEventsButton , logoutButton);

        Scene attendeeScene = new Scene(attendeeMenu, 400, 300);

        // View Profile Button
        viewProfileButton.setOnAction(e -> showProfile());

        // Buy Tickets Button
        buyTicketsButton.setOnAction(e -> showBuyTicketsDialog());
        viewEventsButton.setOnAction(e -> showpurchasedEvents());
        // Logout Button
        logoutButton.setOnAction(e -> primaryStage.setScene(mainScene));

        primaryStage.setScene(attendeeScene);
    }

   private void showCreateCategoryDialog() {
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Create Category");
    dialog.setHeaderText("Enter the details for the new category:");

    
    TextField categoryNameField = new TextField();
    categoryNameField.setPromptText("Category Name");

    TextField categoryDescriptionField = new TextField();
    categoryDescriptionField.setPromptText("Category Description");

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Category Name:"), categoryNameField,
        new Label("Category Description:"), categoryDescriptionField
    );
    dialog.getDialogPane().setContent(dialogContent);

    
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            String categoryName = categoryNameField.getText();
            String categoryDescription = categoryDescriptionField.getText();

            if (categoryName.isEmpty() || categoryDescription.isEmpty()) {
                System.out.println("Category name or description cannot be empty.");
            } else if (!adminList.isEmpty()) {
                adminList.get(0).createCategory(categoryList, categoryName, categoryDescription);
                System.out.println("Category created successfully! Category list size: " + categoryList.size());
            } else {
                System.out.println("No admin available to create a category.");
            }
        }
        return null;
    });

    
    dialog.showAndWait();
}

    private void showCategories() {
    
    Stage categoryStage = new Stage();
    categoryStage.setTitle("Available Categories");

    
    ListView<String> categoryListView = new ListView<>();

    
    if (categoryList.isEmpty()) {
        categoryListView.getItems().add("No categories available.");
    } else {
        for (Category category : categoryList) {
            categoryListView.getItems().add(category.getName() + ": " + category.getDescription());
        }
    }

    
    VBox layout = new VBox(10);
    layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
    layout.getChildren().add(categoryListView);

    
    Scene scene = new Scene(layout, 400, 300);
    categoryStage.setScene(scene);

    
    categoryStage.show();
}
    private void updateCategories() {
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Update Category");
    dialog.setHeaderText("Enter the details to update the category:");

    
    TextField oldCategoryNameField = new TextField();
    oldCategoryNameField.setPromptText("Old Category Name");

    TextField newCategoryNameField = new TextField();
    newCategoryNameField.setPromptText("New Category Name");

    TextField newCategoryDescriptionField = new TextField();
    newCategoryDescriptionField.setPromptText("New Category Description");

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Old Category Name:"), oldCategoryNameField,
        new Label("New Category Name:"), newCategoryNameField,
        new Label("New Category Description:"), newCategoryDescriptionField
    );
    dialog.getDialogPane().setContent(dialogContent);

    
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            String oldName = oldCategoryNameField.getText();
            String newName = newCategoryNameField.getText();
            String newDescription = newCategoryDescriptionField.getText();

            if (oldName.isEmpty() || newName.isEmpty() || newDescription.isEmpty()) {
                System.out.println("All fields must be filled.");
            } else {
                
                boolean categoryUpdated = false;
                for (Category category : categoryList) {
                    if (category.getName().equals(oldName)) {
                        category.setName(newName);
                        category.setDescription(newDescription);
                        categoryUpdated = true;
                        break;
                    }
                }

                if (categoryUpdated) {
                    System.out.println("Category updated successfully!");
                } else {
                    System.out.println("Category with the name '" + oldName + "' not found.");
                }
            }
        }
        return null;
    });

    
    dialog.showAndWait();
}
private void deleteCategories() {
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Delete Category");
    dialog.setHeaderText("Enter the name of the category to delete:");

    
    TextField categoryNameField = new TextField();
    categoryNameField.setPromptText("Category Name");

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Category Name:"), categoryNameField
    );
    dialog.getDialogPane().setContent(dialogContent);

    
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            String categoryName = categoryNameField.getText();

            if (categoryName.isEmpty()) {
                System.out.println("Category name cannot be empty.");
            } else {
                
                boolean categoryDeleted = false;
                for (Category category : categoryList) {
                    if (category.getName().equals(categoryName)) {
                        categoryList.remove(category);
                        categoryDeleted = true;
                        break;
                    }
                }

                if (categoryDeleted) {
                    System.out.println("Category deleted successfully!");
                } else {
                    System.out.println("Category with the name '" + categoryName + "' not found.");
                }
            }
        }
        return null;
    });

    
    dialog.showAndWait();
}
private void showCreateRoomDialog() {
    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Create Room");
    dialog.setHeaderText("Enter the details for the new room:");

    
    TextField roomNumberField = new TextField();
    roomNumberField.setPromptText("Room Number (1-200)");

    TextField capacityField = new TextField();
    capacityField.setPromptText("Capacity (e.g., 50)");

    TextField priceField = new TextField();
    priceField.setPromptText("Price (e.g., 100.0)");

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Room Number:"), roomNumberField,
        new Label("Capacity:"), capacityField,
        new Label("Price:"), priceField
    );
    dialog.getDialogPane().setContent(dialogContent);

    
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            String roomNumberText = roomNumberField.getText();
            String capacityText = capacityField.getText();
            String priceText = priceField.getText();

            if (roomNumberText.isEmpty() || capacityText.isEmpty() || priceText.isEmpty()) {
            
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be filled.");
                alert.showAndWait();
            } else {
                try {
                    int roomNumber = Integer.parseInt(roomNumberText);
                    int capacity = Integer.parseInt(capacityText);
                    double price = Double.parseDouble(priceText);

                    // Validate room number range
                    if (roomNumber < 1 || roomNumber > 200) {
                        throw new IllegalArgumentException("Room number must be between 1 and 200.");
                    }

                    // Validate positive numbers
                    if (capacity <= 0 || price <= 0) {
                        throw new IllegalArgumentException("Capacity and price must be positive numbers.");
                    }

                    // Check for duplicate room numbers
                    for (Rooms room : roomList) {
                        if (room.getRoom_number() == roomNumber) {
                            throw new IllegalArgumentException("Room number already exists.");
                        }
                    }

                    // Create a new room and set its details
                    Rooms newRoom = new Rooms(roomNumber, capacity, price);

                    // Add the room to the global room list
                    roomList.add(newRoom);

                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Room Created");
                    alert.setHeaderText(null);
                    alert.setContentText("Room " + roomNumber + " has been created successfully.");
                    alert.showAndWait();
                } catch (NumberFormatException e) {
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Room number, capacity, and price must be valid numbers.");
                    alert.showAndWait();
                } catch (IllegalArgumentException e) {
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        }
        return null;
    });

   
    dialog.showAndWait();
}
  // Organizer Menu
private void Createvents() {
    if (categoryList.isEmpty()) {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Categories Available");
        alert.setHeaderText(null);
        alert.setContentText("No categories available. Please ask the admin to create categories first.");
        alert.showAndWait();
        return;
    }

    if (roomList.isEmpty()) {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Rooms Available");
        alert.setHeaderText(null);
        alert.setContentText("No rooms available. Please ask the admin to create rooms first.");
        alert.showAndWait();
        return;
    }

    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Create Event");
    dialog.setHeaderText("Enter the details for the new event:");

    
    ComboBox<Category> categoryComboBox = new ComboBox<>();
    categoryComboBox.getItems().addAll(categoryList);
    categoryComboBox.setPromptText("Select a Category");

    
    ComboBox<Rooms> roomComboBox = new ComboBox<>();
    for (Rooms room : roomList) {
        if (!room.isOccupied()) { 
            roomComboBox.getItems().add(room);
        }
    }
    roomComboBox.setPromptText("Select a Room");

    
    TextField eventNameField = new TextField();
    eventNameField.setPromptText("Event Name");

    TextField eventDateField = new TextField();
    eventDateField.setPromptText("Event Date (e.g., 2025-12-31)");

    TextField eventTimeField = new TextField();
    eventTimeField.setPromptText("Event Time (e.g., 18:00)");

    TextField eventPriceField = new TextField();
    eventPriceField.setPromptText("Event Price (e.g., 50.0)");

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Category:"), categoryComboBox,
        new Label("Event Name:"), eventNameField,
        new Label("Event Date:"), eventDateField,
        new Label("Event Time:"), eventTimeField,
        new Label("Event Price:"), eventPriceField,
        new Label("Room:"), roomComboBox
    );
    dialog.getDialogPane().setContent(dialogContent);

    
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            Category selectedCategory = categoryComboBox.getValue();
            String eventName = eventNameField.getText();
            String eventDate = eventDateField.getText();
            String eventTime = eventTimeField.getText();
            String eventPriceText = eventPriceField.getText();
            Rooms selectedRoom = roomComboBox.getValue();

            if (selectedCategory == null || selectedRoom == null || eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || eventPriceText.isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be filled.");
                alert.showAndWait();
            } else {
                try {
                    double eventPrice = Double.parseDouble(eventPriceText);

                    
                    Event newEvent = new Event(eventName, eventDate, eventTime, eventPrice, selectedRoom.getRoom_number());
                    
                    selectedCategory.addevent(newEvent);
                    eventList.add(newEvent);

                   
                    selectedRoom.setOccupied(true);

                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Event Created");
                    alert.setHeaderText(null);
                    alert.setContentText("Event '" + eventName + "' added to category: " + selectedCategory.getName() + " in Room " + selectedRoom.getRoom_number());
                    alert.showAndWait();
                } catch (NumberFormatException e) {
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Event price must be a valid number.");
                    alert.showAndWait();
                }
            }
        }
        return null;
    });

    
    dialog.showAndWait();
}
    private void showEvents() {
    
    Stage eventStage = new Stage();
    eventStage.setTitle("Available Events");

    
    ListView<String> eventListView = new ListView<>();

    
    refreshEventListView(eventListView);

    
    VBox layout = new VBox(10);
    layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
    layout.getChildren().add(eventListView);

   
    Scene scene = new Scene(layout, 400, 300);
    eventStage.setScene(scene);

   
    eventStage.show();
}
private void deleteevent() {
    if (eventList.isEmpty()) {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Events Available");
        alert.setHeaderText(null);
        alert.setContentText("No events available to delete.");
        alert.showAndWait();
        return;
    }

    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Delete Event");
    dialog.setHeaderText("Select an event to delete:");

    
    ComboBox<Event> eventComboBox = new ComboBox<>();
    eventComboBox.getItems().addAll(eventList);
    eventComboBox.setPromptText("Select an Event");

   
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Select Event:"), eventComboBox
    );
    dialog.getDialogPane().setContent(dialogContent);

   
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

   
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            Event selectedEvent = eventComboBox.getValue();
            if (selectedEvent == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Event Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select an event to delete.");
                alert.showAndWait();
            } else {
                
                eventList.remove(selectedEvent);
                eventComboBox.getItems().clear();
                eventComboBox.getItems().addAll(eventList); 

                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Event Deleted");
                alert.setHeaderText(null);
                alert.setContentText("Event '" + selectedEvent.getEventName() + "' has been deleted successfully.");
                alert.showAndWait();
            }
        }
        return null;
    });

    
    dialog.showAndWait();
}

private void refreshEventListView(ListView<String> eventListView) {
    eventListView.getItems().clear();
    if (eventList.isEmpty()) {
        eventListView.getItems().add("No events available.");
    } else {
        for (Event event : eventList) {
            eventListView.getItems().add(event.toString());
        }
    }
}
    private void showattendees() {
        
        Stage attendeeStage = new Stage();
        attendeeStage.setTitle("Available Attendees");

        
        ListView<String> attendeeListView = new ListView<>();

        
        if (attendeeList.isEmpty()) {
            attendeeListView.getItems().add("No attendees available.");
        } else {
            for (finalAtendee attendee : attendeeList) {
                attendeeListView.getItems().add(attendee.toString()); 
            }
        }

       
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().add(attendeeListView);

       
        Scene scene = new Scene(layout, 400, 300);
        attendeeStage.setScene(scene);

        
        attendeeStage.show();
    }
   private void editevent() {
    if (eventList.isEmpty()) {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Events Available");
        alert.setHeaderText(null);
        alert.setContentText("No events available to edit.");
        alert.showAndWait();
        return;
    }

    
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Edit Event");
    dialog.setHeaderText("Select an event to edit:");

   
    ComboBox<Event> eventComboBox = new ComboBox<>();
    eventComboBox.getItems().addAll(eventList);
    eventComboBox.setPromptText("Select an Event");

    
    TextField eventNameField = new TextField();
    eventNameField.setPromptText("Event Name");

    TextField eventDateField = new TextField();
    eventDateField.setPromptText("Event Date (e.g., 2025-12-31)");

    TextField eventTimeField = new TextField();
    eventTimeField.setPromptText("Event Time (e.g., 18:00)");

    TextField eventPriceField = new TextField();
    eventPriceField.setPromptText("Event Price (e.g., 50.0)");

    TextField roomNumField = new TextField();
    roomNumField.setPromptText("Room Number");

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Select Event:"), eventComboBox,
        new Label("Event Name:"), eventNameField,
        new Label("Event Date:"), eventDateField,
        new Label("Event Time:"), eventTimeField,
        new Label("Event Price:"), eventPriceField,
        new Label("Room Number:"), roomNumField
    );
    dialog.getDialogPane().setContent(dialogContent);

    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            Event selectedEvent = eventComboBox.getValue();
            String eventName = eventNameField.getText();
            String eventDate = eventDateField.getText();
            String eventTime = eventTimeField.getText();
            String eventPriceText = eventPriceField.getText();
            String roomNumText = roomNumField.getText();

            if (selectedEvent == null || eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || eventPriceText.isEmpty() || roomNumText.isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be filled.");
                alert.showAndWait();
            } else {
                try {
                    double eventPrice = Double.parseDouble(eventPriceText);
                    int roomNum = Integer.parseInt(roomNumText);

                   
                    selectedEvent.setEventName(eventName);
                    selectedEvent.setEventDate(eventDate);
                    selectedEvent.setEventTime(eventTime);
                    selectedEvent.setEventPrice(eventPrice);
                    selectedEvent.setRoomNum(roomNum);

                   
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Event Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("Event '" + eventName + "' has been updated successfully.");
                    alert.showAndWait();
                } catch (NumberFormatException e) {
                   
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Event price and room number must be valid numbers.");
                    alert.showAndWait();
                }
            }
        }
        return null;
    });

    dialog.showAndWait();
}
   private void showRooms() {
    
    Stage roomStage = new Stage();
    roomStage.setTitle("Available Rooms");

 
    ListView<String> roomListView = new ListView<>();


    if (roomList.isEmpty()) {
        roomListView.getItems().add("No rooms available.");
    } else {
        for (Rooms room : roomList) {
            String status = room.isOccupied() ? "Occupied" : "Available";
            roomListView.getItems().add("Room " + room.getRoom_number() + " (Capacity: " + room.getCapacity() + ", Price: $" + room.getRenting_price() + ") - " + status);
        }
    }


    VBox layout = new VBox(10);
    layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
    layout.getChildren().add(roomListView);

  
    Scene scene = new Scene(layout, 400, 300);
    roomStage.setScene(scene);

 
    roomStage.show();
}
    private void showProfile() {
  
    finalAtendee loggedInAttendee = getLoggedInAttendee();
    if (loggedInAttendee == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Logged-In Attendee");
        alert.setHeaderText(null);
        alert.setContentText("You must log in as an attendee to view the profile.");
        alert.showAndWait();
        return;
    }

  
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Attendee Profile");
    dialog.setHeaderText("Profile Details");

 
    VBox profileContent = new VBox(10);
    profileContent.getChildren().addAll(
        new Label("Username: " + loggedInAttendee.getUsername()),
        new Label("Year of Birth: " + loggedInAttendee.getYearOfBirth()),
        new Label("Address: " + loggedInAttendee.getAddress()),
        new Label("Balance: $" + loggedInAttendee.getBalance()),
        new Label("Gender: " + loggedInAttendee.getGender()),
        new Label("Interests: " + String.join(", ", loggedInAttendee.getInterests()))
    );
    dialog.getDialogPane().setContent(profileContent);

    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

  
    dialog.showAndWait();
}




    private void showBuyTicketsDialog() {
  
    finalAtendee loggedInAttendee = getLoggedInAttendee();
    if (loggedInAttendee == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Logged-In Attendee");
        alert.setHeaderText(null);
        alert.setContentText("You must log in as an attendee to buy tickets.");
        alert.showAndWait();
        return;
    }

    if (eventList.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Events Available");
        alert.setHeaderText(null);
        alert.setContentText("No events are currently available for purchase.");
        alert.showAndWait();
        return;
    }

 
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Buy Tickets");
    dialog.setHeaderText("Select an event to buy tickets:");

    ListView<Event> eventListView = new ListView<>();
    eventListView.getItems().addAll(eventList);

    
    VBox dialogContent = new VBox(10);
    dialogContent.getChildren().addAll(
        new Label("Available Events:"), eventListView
    );
    dialog.getDialogPane().setContent(dialogContent);

   
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

   
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
            Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
            if (selectedEvent == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Event Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select an event to buy tickets.");
                alert.showAndWait();
            } else {
                // Confirm ticket purchase
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirm Purchase");
                confirmationAlert.setHeaderText("Confirm Ticket Purchase");
                confirmationAlert.setContentText("Do you want to buy a ticket for the event: " + selectedEvent.getEventName() + "?");

                Optional<ButtonType> confirmationResult = confirmationAlert.showAndWait();
                if (confirmationResult.isPresent() && confirmationResult.get() == ButtonType.OK) {
                    // Deduct ticket price from attendee's balance
                    double ticketPrice = selectedEvent.getEventPrice();
                    if (loggedInAttendee.getBalance() >= ticketPrice) {
                        loggedInAttendee.setBalance(loggedInAttendee.getBalance() - ticketPrice);
                        selectedEvent.addPurchaser(loggedInAttendee); 
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Purchase Successful");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("You have successfully purchased a ticket for the event: " + selectedEvent.getEventName() + ".\nRemaining Balance: $" + loggedInAttendee.getBalance());
                        successAlert.showAndWait();
                    } else {
                        Alert insufficientFundsAlert = new Alert(Alert.AlertType.ERROR);
                        insufficientFundsAlert.setTitle("Insufficient Funds");
                        insufficientFundsAlert.setHeaderText(null);
                        insufficientFundsAlert.setContentText("You do not have enough balance to buy a ticket for this event.");
                        insufficientFundsAlert.showAndWait();
                    }
                }
            }
        }
        return null;
    });

    
    dialog.showAndWait();
}


private void showpurchasedEvents() {
   
    finalAtendee loggedInAttendee = getLoggedInAttendee();
    if (loggedInAttendee == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Logged-In Attendee");
        alert.setHeaderText(null);
        alert.setContentText("You must log in as an attendee to view purchased events.");
        alert.showAndWait();
        return;
    }

    
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Purchased Events");
    dialog.setHeaderText("Your Purchased Events");

    
    VBox purchasedEventsContent = new VBox(10);
    boolean hasPurchasedEvents = false;
    for (Event event : eventList) {
        if (event.isPurchasedBy(loggedInAttendee)) {
            purchasedEventsContent.getChildren().add(new Label(event.toString()));
            hasPurchasedEvents = true;
        }
    }

    if (!hasPurchasedEvents) {
        purchasedEventsContent.getChildren().add(new Label("You have not purchased any events."));
    }

    dialog.getDialogPane().setContent(purchasedEventsContent);

   
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

   
    dialog.showAndWait();
}


  private boolean validateLogin(String username, String password, String userType) {
    switch (userType) {
        case "Admin":
            for (Admin admin : adminList) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    return true;
                }
            }
            break;

        case "Organizer":
            for (Organizer organizer : organizerList) {
                if (organizer.getUsername().equals(username) && organizer.getPassword().equals(password)) {
                    return true;
                }
            }
            break;

        case "Attendee":
            for (finalAtendee attendee : attendeeList) {
                if (attendee.getUsername().equals(username) && attendee.getPassword().equals(password)) {
                    loggedInAttendee = attendee; 
                    return true;
                }
            }
            break;

        default:
            System.out.println("Invalid user type.");
            return false;
    }

    return false;
}

   
    private finalAtendee getLoggedInAttendee() {
        return loggedInAttendee;
    }

   

private boolean registerUser(String username, String password, String userType) {
    if (username.isEmpty() || password.isEmpty()) {
        System.out.println("Username or password cannot be empty.");
        return false;
    }

    switch (userType) {
        case "Admin":
            for (Admin admin : adminList) {
                if (admin.getUsername().equals(username)) {
                    System.out.println("Admin username already exists.");
                    return false;
                }
            }
            Admin newAdmin = new Admin(username, password, "01-01-1990", "Manager", "9 AM - 5 PM");
            adminList.add(newAdmin);
            System.out.println("Admin registered successfully! Admin list size: " + adminList.size());
            return true;

        case "Organizer":
            for (Organizer organizer : organizerList) {
                if (organizer.getUsername().equals(username)) {
                    System.out.println("Organizer username already exists.");
                    return false;
                }
            }
            Organizer newOrganizer = new Organizer(username, password);
            organizerList.add(newOrganizer);
            System.out.println("Organizer registered successfully! Organizer list size: " + organizerList.size());
            return true;

        case "Attendee":
            for (finalAtendee attendee : attendeeList) {
                if (attendee.getUsername().equals(username)) {
                    System.out.println("Attendee username already exists.");
                    return false;
                }
            }

           
            Dialog<Boolean> dialog = new Dialog<>();  
            dialog.setTitle("Attendee Registration");
            dialog.setHeaderText("Enter additional details for the attendee:");

            // Create input fields for attendee registration
            TextField yearOfBirthField = new TextField();
            yearOfBirthField.setPromptText("Year of Birth (e.g., 1990)");

            TextField addressField = new TextField();
            addressField.setPromptText("Address");

            TextField balanceField = new TextField();
            balanceField.setPromptText("Balance (e.g., 100.0)");

            ComboBox<finalAtendee.Gender> genderComboBox = new ComboBox<>();
            genderComboBox.getItems().addAll(finalAtendee.Gender.values());
            genderComboBox.setPromptText("Select Gender");

            TextField interestsField = new TextField();
            interestsField.setPromptText("Interests (comma-separated)");

            
            VBox dialogContent = new VBox(10);
            dialogContent.getChildren().addAll(
                new Label("Year of Birth:"), yearOfBirthField,
                new Label("Address:"), addressField,
                new Label("Balance:"), balanceField,
                new Label("Gender:"), genderComboBox,
                new Label("Interests:"), interestsField
            );
            dialog.getDialogPane().setContent(dialogContent);

          
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            
            dialog.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    try {
                        int yearOfBirth = Integer.parseInt(yearOfBirthField.getText());
                        if (yearOfBirth < 1900 || yearOfBirth > 2025) {
                            throw new NumberFormatException("Year of birth must be between 1900 and 2025.");
                        }

                        double balance = Double.parseDouble(balanceField.getText());
                        if (balance < 0) {
                            throw new NumberFormatException("Balance must be a positive number.");
                        }

                        finalAtendee.Gender gender = genderComboBox.getValue();
                        if (gender == null) {
                            throw new IllegalArgumentException("Gender must be selected.");
                        }

                        String interestsText = interestsField.getText();
                        if (interestsText.isEmpty()) {
                            throw new IllegalArgumentException("Interests cannot be empty.");
                        }

                        List<String> interests = List.of(interestsText.split(","));

                        finalAtendee newAttendee = new finalAtendee();
                        newAttendee.setUsername(username);
                        newAttendee.setPassword(password);
                        newAttendee.setYearOfBirth(yearOfBirth);
                        newAttendee.setAddress(addressField.getText());
                        newAttendee.setBalance(balance);
                        newAttendee.setGender(gender);
                        newAttendee.setInterests(interests);

                        attendeeList.add(newAttendee);
                        System.out.println("Attendee registered successfully! Attendee list size: " + attendeeList.size());
                        return true;  
                    } catch (IllegalArgumentException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }
                return false;  
            });

            
            Optional<Boolean> result = dialog.showAndWait();
            return result.orElse(false);  

        default:
            System.out.println("Invalid user type.");
            return false;
    }
}

    public static void main(String[] args) {
        launch(args);
    }
}