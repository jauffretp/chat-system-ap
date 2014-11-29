package modelMessage;

public abstract class MessageFactory {

    public enum MessageFormat {

        JSON
    };

    public static Message getMessageObject(MessageFormat choice) {
        switch (choice) {
            case JSON:
                return new JSONMessage();
            default:
                return null;
        }
    }
}
