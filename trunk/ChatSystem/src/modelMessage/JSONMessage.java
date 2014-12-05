package modelMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONMessage extends JSONObject implements Message {

    public JSONMessage() {
        super();
    }

    public JSONMessage(String data) throws JSONException {
        super(data);
    }

    @Override
    public String getMessageData() {
        String returnValue = " ";
        try {
            returnValue = (String) this.get("messageData");
        } catch (JSONException ex) {
            System.err.println("JSONMessage : Can't get messageData value");
        }
        return returnValue;
    }

    @Override
    public String getUserName() {
        String returnValue = " ";
        try {
            returnValue = (String) this.get("userName");
        } catch (JSONException ex) {
            System.err.println("JSONMessage : Can't get userName value");
        }
        return returnValue;
    }

    @Override
    public String getType() {
        String returnValue = " ";
        try {
            returnValue = (String) this.get("type");
        } catch (JSONException ex) {
            System.err.println("JSONMessage : Can't get type value");
        }
        return returnValue;
    }

    @Override
    public int getMessageNumber() {
        int returnValue = -2;
        try {
            returnValue = this.getInt("messageNumber") ; 
        } catch (JSONException ex) {
            System.err.println("JSONMessage : Can't get messageNumber value");
        }
        return returnValue;
    }

    @Override
    public int getIntType() {
        try {
            String type = (String) this.get("type");

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
            System.err.println("JSONMessage : This JSON has no \"type\" attribute.");
            return UNKNOWN;
        }
    }

}
