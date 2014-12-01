package network;

public interface CtrlToNI {

    // sending side
    public void sendHello(String nickname);

    public void sendHelloAck(String local_username, String ip);

    public void sendGoodbye();

    public void sendMessage(String username, String ip, String message, int messageNumber);

    public void sendMessageAck(String ip, int messageNumber) ; 

    public void sendFileTo(String filePath, String username);

    // receiving side    
    public void processHello(String nickname, String ip);

    public void processHelloAck(String nickname, String ip);

    public void processGoodBye(String nickname);

    public void processMessage(String nickname, String dataMessage);

    public void processMessageAck(int messageNumber);
}
