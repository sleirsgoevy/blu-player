package org.dvb.event;

import java.util.HashSet;

public class UserEventRepository
{
    HashSet addedKeys;
    public UserEventRepository(String name)
    {
        addedKeys = new HashSet();
    }
    public void addKey(int key)
    {
        addedKeys.add(new Integer(key));
    }
    public void addAllArrowKeys()
    {
        addKey(37);
        addKey(38);
        addKey(39);
        addKey(40);
    }
    public void addAllColourKeys()
    {
        addKey(403);
        addKey(404);
        addKey(405);
        addKey(406);
    }
    public void addAllNumericKeys()
    {
        for(int i = 48; i < 58; i++)
            addKey(i);
    }
}
