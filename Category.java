import java.util.ArrayList;
import java.util.List;
public class Category {
    private String name;
    private String description;
    private List<Event> events;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        events = new ArrayList<Event>();
        
    }

    public void addevent(Event event) {
        // add category
        events.add(event);
        System.out.println("Event added: " + event.toString());
    
    }
    public List<Event> readevents() {
        // read category
        return events;
    }
    public void deleteevent(Event event) {
        // delete category
        events.remove(event);
        System.out.println("Event deleted: " + event.toString());
    }

    public void showEvents() {
        System.out.println("Events in category: " + name);
        for (Event e : events) {
            System.out.println(e);
        }
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
 @Override
    public String toString() {
        return "Category{name='" + name + "', description='" + description + "'}";
    }
}
