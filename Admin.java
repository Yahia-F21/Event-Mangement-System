import java.util.List;

public class Admin  {
    private String role;
    private String workingHours;
    private String username;
    private String password;
    private String dateOfBirth;

    public Admin(String username, String password, String dateOfBirth, String role, String workingHours) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.workingHours = workingHours;
    }


    // ----- CATEGORY CRUD -----

    // CREATE
    public void createCategory(List<Category> categories, String name, String description) {
        Category newCategory = new Category(name, description);
        categories.add(newCategory);
        System.out.println("Category added: " + name);
    }

    // READ
    public void viewCategories(List<Category> categories) {
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }
    
        System.out.println("All Categories:");
        for (Category category : categories) {
            System.out.println("Category: " + category.getName() + " - " + category.getDescription());
            List<Event> events = category.readevents();
            if (events.isEmpty()) {
                System.out.println("  No events in this category.");
            } else {
                System.out.println("  Events:");
                for (Event event : events) {
                    System.out.println("    " + event); // Assuming Event class has a proper toString() method
                }
            }
        }
    }

    // UPDATE
    public void updateCategory(List<Category> categories, String oldName, String newName, String newDescription) {
        for (Category cat : categories) {
            
                cat.setName(newName);
                cat.setDescription(newDescription);
                System.out.println("Category updated: " + newName);
                return;
            
        }
        System.out.println("Category not found: " + oldName);
    }

    // DELETE
    public void deleteCategory(List<Category> categories, String name) {
        boolean found = false;
        for (Category cat : categories) {
           
                categories.remove(cat);
                System.out.println("Category deleted: " + name);
                found = true;
                return;
            
        }
        if (found) {
            System.out.println("Category deleted: " + name);
        } else {
            System.out.println("Category not found: " + name);
        }
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
