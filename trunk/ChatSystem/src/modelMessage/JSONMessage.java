package modelMessage;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMessage extends JSONObject implements Message {

    public JSONMessage() {
        super();
    }

    // JSONMessage Object treatments \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    // initilaizes a Message object from Strings     
    @Override
    public void initMessage(String type, String userName, String messageData, int messageNumber) {
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

    @Override
    public String getMessageData() {
        String returnValue = " ";
        try {
            returnValue = (String) this.get("messageData");
        } catch (JSONException ex) {
            System.out.println("JSONMessage : Can't get messageData value");
        }
        return returnValue;        
    }

    @Override
    public String getUserName() {
        String returnValue = " ";
        try {
            returnValue = (String) this.get("userName");
        } catch (JSONException ex) {
            System.out.println("JSONMessage : Can't get userName value");
        }
        return returnValue;
    }

    @Override
    public String getType() {
        String returnValue = " ";
        try {
            returnValue = (String) this.get("type");
        } catch (JSONException ex) {
            System.out.println("JSONMessage : Can't get type value");
        }
        return returnValue;
    }

    public int getMessageNumber() {
        int returnValue = -2 ;
        try {
            returnValue = Integer.parseInt(this.get("messageNumber").toString()) ; 
        } catch (JSONException ex) {
            System.out.println("JSONMessage : Can't get messageNumber value");
        }
        return returnValue;
    }
    ////////////////////////////////////////////////////////////////////////

    // JSONObject treatments \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Override
    public int typeMessage(Object obj) {
        JSONObject jsonObj = (JSONObject) obj;
        try {
            String type = (String) jsonObj.get("type");

            if (type.equals("hello")) {
                return HELLO;
            } else if (type.equals("helloAck")) {
                return HELLO_ACK;
            } else if (type.equals("message")) {
                return MESSAGE;
            } else if (type.equals("messageAck")) {
                return MESSAGE_ACK;
            } else if (type.equals("goodBye")) {
                return GOODBYE;
            } else {
                return UNKNOWN;
            }

        } catch (JSONException ex) {
            System.out.println("JSONMessage : This JSON has no \"type\" attribute.");
            return UNKNOWN;
        }
    }

    // init a JSONMessage object from a JSONObject (parsing)   
    @Override
    public void initMessage(Object obj) {

        if (obj instanceof DatagramPacket) {
            try {
                initMessage(new JSONObject(new String(((DatagramPacket) obj).getData(), "UTF-8")));
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Message : Error with the encoding");
            } catch (JSONException ex) {
                System.out.println("Message : Error with the packet (not JSON)");
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jsonObj;
            jsonObj = (JSONObject) obj;
            try {
                int type = typeMessage(jsonObj);
                switch (type) {
                    case HELLO:
                    case HELLO_ACK:
                        this.initMessage((String) jsonObj.get("type"), (String) jsonObj.get("userName"), " ", -1);
                        break;
                    case MESSAGE:
                        this.initMessage((String) jsonObj.get("type"), " ", (String) jsonObj.get("messageData"), Integer.parseInt(jsonObj.get("messageNumber").toString()));
                        break;
                    case MESSAGE_ACK:
                        this.initMessage((String) jsonObj.get("type"), " ", " ", Integer.parseInt(jsonObj.get("messageNumber").toString()));
                        break;
                    case GOODBYE:
                        this.initMessage((String) jsonObj.get("type"), " ", " ", -1);
                        break;
                    default:
                        System.out.println("Message : Error initializing JSONMessage from JSON object");
                }

            } catch (JSONException ex) {
                System.out.println("Message : Error with initMessage(JSON)");
                ex.printStackTrace();
            }
        } else {
            System.out.println("Message : object non detected !");
        }
    }

   ////////////////////////////////////////////////////////////////////// 
}
