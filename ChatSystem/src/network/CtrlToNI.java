package network;

import java.io.File;

public interface CtrlToNI {

    // sending side
    public void sendHello(String nickname);

    public void sendHelloAck(String local_username, String ip);

    public void sendGoodbye();

    public void sendMessage(String username, String ip, String message, String messageNumber);

    public void sendMessageAck(String local_username, int messageNumber);

    public void sendFileTo(File file, String username);

    // receiving side    
    public void processHello(String nickname, String ip);

    public void processHelloAck(String nickname, String ip);

    public void processGoodBye(String nickname);

    public void processMessage(String nickname, String dataMessage);

    public void processMessageAck(String nickname);
}
