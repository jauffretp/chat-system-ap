
package model;

import java.net.InetAddress;


public class User {

    private String nickname ; 
    private String ip ;

    public User(String nickname, String ip) {
        this.nickname = nickname;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return nickname + "@" + ip ;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIp() {
        return ip;
    }   
    
    public Boolean equals(User anotherUser){
        return (anotherUser.getNickname().equals(this.nickname) && anotherUser.getIp().equals(this.ip));
    }
    
}