package com.catolicasc.combatcontrol;

public class Nocaute {
    private static Nocaute mInstance= null;

    public boolean nocaute;

    protected Nocaute(){}

    public static synchronized Nocaute getInstance() {
        if(null == mInstance){
            mInstance = new Nocaute();
        }
        return mInstance;
    }
}
