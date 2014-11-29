
package model;


public abstract class MessageFactory {
    
    public enum MessageFormat {JSON};

    public static Message getMessageObject(MessageFormat choice){
         switch(choice) {
             case JSON:
                 return new Message() ; 
             default:
                 return null;
    }
}
}
