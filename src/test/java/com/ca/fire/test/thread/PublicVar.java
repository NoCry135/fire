package com.ca.fire.test.thread;

public class PublicVar {

    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username,String password){
        try{
            this.username = username;
            Thread.sleep(3000);
            this.password = password;
            System.out.println("setValue method thread name=" + Thread.currentThread().getName() + " username="
                    + username + " password=" + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void getValue(){
        System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username
                + " password=" + password);
    }
}
