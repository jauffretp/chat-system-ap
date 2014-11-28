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
            String type = (String) jsonObj.get("type");
            if(type.equals("hello") || type.equals( "helloAck")){
                this.initMessage((String) jsonObj.get("type"), (String) jsonObj.get("userName")," ", " ");
            }
            else if(type.equals( "goodBye")){
                this.initMessage((String) jsonObj.get("type")," "," "," ");
            }
            else if(type.equals( "message")){
                this.initMessage((String) jsonObj.get("type")," ", (String) jsonObj.get("messageData"), (String) jsonObj.get("messageNumber"));
            }
             else if(type.equals( "messageAck")){
                 this.initMessage((String) jsonObj.get("type")," " , " ", (String) jsonObj.get("messageNumber"));
             }
            
        } catch (JSONException ex) {
            System.out.println("Error with initMessage(JSON)");
            ex.printStackTrace();
        }
    }
    
}
