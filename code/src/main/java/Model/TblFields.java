package Model;

import java.util.*;

public class TblFields {

    public static Map<String, List<String>> enumDict = new HashMap<String, List<String>>();

    public static void init() {
        List<String> enumList = new ArrayList<String>();
        enumList = Arrays.asList("username", "psw", "name", "organization",
                "rank", "accountStatus", "email", "warnings");
        enumDict.put("user", enumList);

        enumList = Arrays.asList("organizationName", "adminUsername");
        enumDict.put("organization", enumList);

        enumList = Arrays.asList("eventId", "title", "eventDate", "eventStatus",
                "Police", "EMS", "FD", "creator");
        enumDict.put("event", enumList);

        enumList = Arrays.asList("participateEventId", "participateUsername");
        enumDict.put("eventAndParticipate", enumList);

        enumList = Arrays.asList("feedbackEventId", "feedbacker", "feedbackee", "feedback");
        enumDict.put("feedback", enumList);

        enumList = Arrays.asList("updateId","event", "updateDate", "publisher", "updateEventId", "lastUpdateId");
        enumDict.put("updates", enumList);
        //TODO add description.
        //TODO remove updateEventId
        //TODO remove lastUpdateID
        //TODO change event to Int, not text

        enumList = Arrays.asList("updateVersionId", "version", "description");
        enumDict.put("updateVersion", enumList);

        enumList = Arrays.asList("complaintId", "complainer", "complainee", "description", "status");
        enumDict.put("complaint", enumList);

        enumList = Arrays.asList("categoryName");
        enumDict.put("categories", enumList);

        enumList = Arrays.asList("eventCategoryName", "ecEventId");
        enumDict.put("eventAndCategory", enumList);

        enumList = Arrays.asList("massageName", "accountUsername", "isNew", "massage");
        enumDict.put("accountMassages", enumList);
    }
}