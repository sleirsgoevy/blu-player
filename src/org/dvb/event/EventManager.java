package org.dvb.event;

import java.util.ArrayList;
import java.util.HashSet;

public class EventManager
{
    private ArrayList callbacks;
    private ArrayList repos;
    private static EventManager instance;
    static
    {
        instance = new EventManager();
    }
    private EventManager()
    {
        callbacks = new ArrayList();
        repos = new ArrayList();
    }
    public static EventManager getInstance()
    {
        return instance;
    }
    public void addUserEventListener(UserEventListener cb, UserEventRepository repo)
    {
        callbacks.add(cb);
        repos.add(repo);
    }
    private static int translateKey(int key)
    {
        if(key == 87)
            return 425;
        else if(key == 83)
            return 424;
        else if(key == 65)
            return 412;
        else if(key == 68)
            return 417;
        else if(key == 27)
            return 19;
        else if(key == 32)
            return 415;
        else if(key == 47)
            return 461;
        else if(key >= 37 && key <= 40 || key >= 48 && key < 58 || key == 10)
            return key;
        return 0;
    }
    public static void _injectEvent(int type, int key)
    {
        key = translateKey(key);
        if(key == 0)
            return;
        Integer i_key = new Integer(key);
        for(int i = 0; i < instance.repos.size(); i++)
        {
            UserEventRepository repo = (UserEventRepository)instance.repos.get(i);
            if(repo.addedKeys.contains(i_key))
                ((UserEventListener)instance.callbacks.get(i)).userEventReceived(new UserEvent(type, key));
        }
    }
}
