package javax.media;

import java.util.EventListener;

public interface ControllerListener extends EventListener
{
    void controllerUpdate(ControllerEvent paramControllerEvent);
}
