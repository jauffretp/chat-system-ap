package modelMessage;

public abstract class MessageFactory {

    public static MessageFactory getFactory(MessageFormat choice) {
        if (choice == MessageFormat.JSON) {
            return new JSONMessageFactory();
        } else {
            return null;
        }
    }

    public abstract String returnEncoding() ; 
    
    public abstract Message createEmptyMessage();

    public abstract Message createMessageFromValues(String type, String userName, String messageData, int messageNumber);

    public abstract Message createMessageFromNIObject(Object NIObject);

}
