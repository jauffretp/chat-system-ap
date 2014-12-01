package modelMessage;

public interface Message {

    public static final int HELLO = 0;
    public static final int HELLO_ACK = 1;
    public static final int MESSAGE = 2;
    public static final int MESSAGE_ACK = 3;
    public static final int GOODBYE = 4;
    public static final int UNKNOWN = 5;

    public int getIntType() ; 
    
    public String getType();
    
    public int getMessageNumber();

    public String getMessageData();

    public String getUserName();

}
