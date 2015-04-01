package com.johndoll.bluesourceselenium.utility;

/**
 * @author Jonathan Doll
 */

public class Wait {
    
    public Wait(){
    }
    
    public void waitSec(double seconds){
        long timer = System.currentTimeMillis();
        while(System.currentTimeMillis() - timer < seconds * 1000);
    }
    
    public void waitMin(double minutes){
        long timer = System.currentTimeMillis();
        while(System.currentTimeMillis() - timer < minutes * 60000);
    }
    
    public void waitMilSec(double milliseconds){
        long timer = System.currentTimeMillis();
        while(System.currentTimeMillis() - timer < milliseconds);
    }
    
}
