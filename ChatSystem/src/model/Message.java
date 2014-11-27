package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Message extends JSONObject {

    public Message() {
        super();
    }

    public void initMessage(String type, String userName, String messageData, String messageNumber) {
        try {
            this.put("type", type);
            this.put("userName", userName);
            this.put("messageData", messageData);
            this.put("messageNumber", messageNumber);
        } catch (JSONException ex) {
            System.out.println("Error with initMessage(String))");
            ex.printStackTrace();
        }
    }

    public void initMessage(JSONObject jsonObj) {
        try {
            this.initMessage((String) jsonObj.get("type"), (String) jsonObj.get("userName"), (String) jsonObj.get("messageData"), (String) jsonObj.get("messageNumber"));
        } catch (JSONException ex) {
            System.out.println("Error with initMessage(JSON)");
            ex.printStackTrace();
        }
    }
}
