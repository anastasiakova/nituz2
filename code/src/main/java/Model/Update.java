package Model;

import java.util.Date;

public class Update {
    private int id;
    private String description;
    private Date publishDate;
    private String publisherUsername;
    private int event;


    public Update(String username, String description, int eventId) {

    }

    public Update(String updateRow) {

    }
}