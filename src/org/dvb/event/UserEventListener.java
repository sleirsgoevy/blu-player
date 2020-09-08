package org.dvb.event;

import java.util.EventListener;

public interface UserEventListener extends EventListener
{
    void userEventReceived(UserEvent e);
}
