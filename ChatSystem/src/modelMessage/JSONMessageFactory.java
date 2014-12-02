package modelMessage;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

import jsonLib.JSONException ;
import static modelMessage.Message.*;
 

public class JSONMessageFactory extends MessageFactory {

    private final String encoding = "UTF-8" ; 
    
    @Override
    public String returnEncoding() { return encoding ; } 
    
    @Override
    public JSONMessage createEmptyMessage() {
        return new JSONMessage();
    }

    @Override
    // initilaizes a JSONMessage object from Strings 
    public JSONMessage createMessageFromValues(String type, String userName, String messageData, int messageNumber) {
        JSONMessage json = new JSONMessage();
        try {
            json.put("type", type);
            json.put("userName", userName);
            json.put("messageData", messageData);
            json.put("messageNumber", messageNumber);
        } catch (JSONException ex) {
            System.err.println("JSONMessageFactory : Error with initMessage(String))");
            ex.printStackTrace();
        }
        return json;
    }

    private JSONMessage DatagramToJSONMessage(DatagramPacket dp) throws JSONException, UnsupportedEncodingException {
        return new JSONMessage(new String(dp.getData(), encoding));
    }

    // init a JSONMessage object from a NIObject (DatagramPacket for exemple)  
    @Override
    public JSONMessage createMessageFromNIObject(Object obj) {
        JSONMessage json = null;

        if (obj instanceof DatagramPacket) {
            try {
                json = new JSONMessage(new String(((DatagramPacket) obj).getData(), encoding));

                int type = json.getIntType();

                /* according to the type, the function creates a JSONMessage object. 
                 ** Among the 4 keys of a JSONMessage, f
                 ** for keys with no real values (according to specifications) 
                 ** we put " " as String value or -1 as int value
                 */
                switch (type) {
                    case HELLO:
                    case HELLO_ACK:
                        json = createMessageFromValues((String) json.get("type"), (String) json.get("userName"), " ", -1);
                        break;
                    case MESSAGE:
                        json = createMessageFromValues((String) json.get("type"), " ", (String) json.get("messageData"), Integer.parseInt(json.get("messageNumber").toString()));
                        break;
                    case MESSAGE_ACK:
                        json = createMessageFromValues((String) json.get("type"), " ", " ", Integer.parseInt(json.get("messageNumber").toString()));
                        break;
                    case GOODBYE:
                        json = createMessageFromValues((String) json.get("type"), " ", " ", -1);
                        break;
                    default:
                        System.out.println("Message : Error initializing JSONMessage from JSON object");
                }
            } catch (JSONException ex) {
                System.err.println("JSONFactoryMessage : JSONObject not correct (doesn't have the good 4 keys)");
                ex.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                System.err.println("JSONFactoryMessage : Error with the encoding");
            }
        } else {
            System.err.println("JSONFactoryMessage : NI Object type non detected !");
        }

        return json;
    }

}
