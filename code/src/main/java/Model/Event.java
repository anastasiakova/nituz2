package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Event{
    private String title;
    private Status status;
    private Date date;
    private Category category;
    private HashMap<Organizations, OrganizationUser> inCharge;
    private HashMap<User, Permission> Participate;
    private EOCUser creator;
    private ArrayList<Update> updates;

    enum  Status {
        inTreatment,
        treatmentOver
    }
    enum Permission{
        read,
        write
    }
}