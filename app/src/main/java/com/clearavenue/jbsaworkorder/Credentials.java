package com.clearavenue.jbsaworkorder;

/**
 * Created by shane.hogan on 1/6/2016.
 */
public class Credentials {

    private String userName;
    private String password;

    public Credentials(){
        this.userName = "";
        this.password = "";
    }
    /**
     *
     * @param userName
     * @param password
     */
    public Credentials(String userName, String password){
        super();
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @return the username
     */
    public String getUserName(){
        return userName;
    }

    /**
     *
     * @return the password
     */
    public String getPassword(){
        return password;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "Credentials [userName = " + userName + ", password = " + password +"]\n";
    }
}
