package org.dvb.event;

public class UserEvent
{
    private int type;
    private int code;
    UserEvent(int type, int code)
    {
        this.type = type;
        this.code = code;
    }
    public int getType()
    {
        return type;
    }
    public int getCode()
    {
        return code;
    }
}
