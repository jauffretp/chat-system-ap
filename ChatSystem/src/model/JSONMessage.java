package model;

import java.net.DatagramPacket;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMessage extends JSONObject {

    public static final int HELLO = 0;
    public static final int HELLO_ACK = 1;
    public static final int MESSAGE = 2;
    public static final int MESSAGE_ACK = 3;
    public static final int GOODBYE = 4;
    public static final int UNKNOWN = 5;
        
        
    public JSONMessage() {
        super();
    }    
    
    // JSONMessage Object treatments \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    // initilaizes a Message object from Strings 
    
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
    
    
    
    ////////////////////////////////////////////////////////////////////////
    
    
    // JSONObject treatments \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    public int typeMessage(JSONObject jsonObj){
        try {
            String type = (String) jsonObj.get("type");
            
            if(type.equals("hello")) return HELLO ; 
            else if (type.equals( "helloAck")) return HELLO_ACK ; 
            else if (type.equals( "message")) return MESSAGE ;
            else if (type.equals( "messageAck"))return MESSAGE_ACK ;            
            else if(type.equals( "goodBye"))return GOODBYE ;
            else return UNKNOWN ; 
            
        } catch (JSONException ex) {
            System.out.println("Message : This JSON has no \"type\" attribute.");
            return UNKNOWN ;
        }
    }   
 
    // init a JSONMessage object from a JSONObject (parsing)   
    public void initMessage(Object obj) {
         
        if (obj instanceof DatagramPacket){ 
            try {
                initMessage(new JSONObject(new String(((DatagramPacket) obj).getData(), "UTF-8")));
            } catch (Exception ex) {
                System.out.println("Message : Error with the packet");
            }
        }
        else if (obj instanceof JSONObject){
        JSONObject jsonObj ;
        jsonObj = (JSONObject) obj;
                try {
            int  type = typeMessage(jsonObj) ; 
            switch(type){
                case HELLO:
                case HELLO_ACK: 
                    this.initMessage((String) jsonObj.get("type"), (String) jsonObj.get("userName")," ", " ");
                    break; 
                case MESSAGE:
                    this.initMessage((String) jsonObj.get("type"), " " , (String) jsonObj.get("messageData"), (String) jsonObj.get("messageNumber"));
                    break;
                case MESSAGE_ACK:
                    this.initMessage((String) jsonObj.get("type"), " " , " ", (String) jsonObj.get("messageNumber"));
                    break;  
                case GOODBYE:
                    this.initMessage((String) jsonObj.get("type")," "," "," ");
                    break;                 
                default: System.out.println("Message : Error initializing JSONMessage from JSON object");
            }     
                        
        } catch (JSONException ex) {
            System.out.println("Message : Error with initMessage(JSON)");
            ex.printStackTrace();
        }
        }
        else {
            System.out.println("Message : object non detected !");
        }
    }
    
   ////////////////////////////////////////////////////////////////////// 
    
    
    
}
