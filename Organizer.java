import java.util.ArrayList;
import java.util.List;


public class Organizer
{
    private String user;
    private String pass;
    Wallet wallet ; // ma7fazet el organizer
    ArrayList<Event> events; // mgmo3et el events
    

    Organizer(String user, String pass)
    {
        if (user == null || user.trim().isEmpty())
        {
            throw new IllegalArgumentException("Username can't be null or empty");
        }
        this.user = user;

        if (pass == null || pass.trim().isEmpty())
        {
            throw new IllegalArgumentException("Password can't be null or empty");
        }
        this.pass = pass;

      
        this.events = new ArrayList<>();

    }




    public String getUsername()
    {
        return user;
    }
    public String getPassword()
    {
        return pass;
    }
    public Wallet get_wallet()
    {
        return wallet;
    }


    // manage events
    public void add_event(Event event)
    {
        if (event == null)
        {
            throw new IllegalArgumentException("Event can't be null");
        }
        events.add(event);
    }
    //  public void addevent(Event event) {
    //     // add category
    //     events.add(event);
    //     System.out.println("Event added: " + event.toString());
    
    // }
    public List<Event> readevents() {
        // read category
        return events;
    }
    // public void deleteevent(Event event) {
    //     // delete category
    //     events.remove(event);
    //     System.out.println("Event deleted: " + event.toString());
    // }

    // public void showEvents() {
    //     System.out.println("Events in category: " + name);
    //     for (Event e : events) {
    //         System.out.println(e);
    //     }
    // }
    public void del_event(int i)
    {
        if (i < 0 || i >= events.size()) {
            throw new IndexOutOfBoundsException("wrong index: " +"\n Valid range: 0 to " + (events.size() - 1));
        }   
        events.remove(i);
    }
    public void change_event(int i, Event event) {
        if (i < 0 || i >= events.size()) {
            throw new IndexOutOfBoundsException("Invalid index for changing event: " + i);
        }
        if (event == null) {
            throw new IllegalArgumentException("New event cannot be null.");
        }
        events.set(i, event);
        System.out.println("Event updated successfully.");
    }



    public void receive_payment(double amount)
    {
        wallet.inc_balance(amount);
    }


}
