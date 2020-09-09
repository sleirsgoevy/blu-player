package javax.media;

import java.util.HashSet;
import java.util.Iterator;

public interface Player
{
    public void realize();
    public void prefetch();
    public void start();
    public void stop();
    public void close();
    public void addControllerListener(ControllerListener cl);
    public void removeControllerListener(ControllerListener cl);
    public long getMediaNanoseconds();
    public int getState();
    static class PlayerImpl implements Player
    {
        private static long STUB_MEDIA_DURATION = 1000000000;
        private boolean running = false;
        private boolean started = false;
        private long time = 0;
        public void realize(){}
        public void prefetch(){}
        private HashSet lst;
        public PlayerImpl()
        {
            lst = new HashSet();
        }
        private class NotifierThread extends Thread
        {
            private PlayerImpl self;
            private long duration;
            public NotifierThread(PlayerImpl s, long d)
            {
                self = s;
                duration = d;
            }
            public void run()
            {
                synchronized(self)
                {
                    ControllerEvent e = new StartEvent();
                    Iterator i = self.lst.iterator();
                    while(i.hasNext())
                    {
                        ControllerListener cl = (ControllerListener)i.next();
                        cl.controllerUpdate(e);
                    }
                }
                try
                {
                    Thread.sleep(duration / 1000000);
                }
                catch(InterruptedException e)
                {
                    return;
                }
                synchronized(self)
                {
                    ControllerEvent e = new EndOfMediaEvent();
                    Iterator i = self.lst.iterator();
                    while(i.hasNext())
                    {
                        ControllerListener cl = (ControllerListener)i.next();
                        cl.controllerUpdate(e);
                    }
                }
            }
        }
        private NotifierThread notifier;
        public synchronized void start()
        {
            if(!running)
            {
                notifier = new NotifierThread(this, STUB_MEDIA_DURATION - time);
                notifier.start();
                time = System.currentTimeMillis() - time;
                running = true;
                started = true;
            }
        }
        public synchronized void stop()
        {
            if(running)
            {
                notifier.interrupt();
                notifier = null;
                time = System.currentTimeMillis() - time;
                running = false;
                ControllerEvent e = new StopEvent();
                Iterator i = lst.iterator();
                while(i.hasNext())
                {
                    ControllerListener cl = (ControllerListener)i.next();
                    cl.controllerUpdate(e);
                }
            }
        }
        public void close(){}
        public synchronized void addControllerListener(ControllerListener cl)
        {
            lst.add(cl);
        }
        public synchronized void removeControllerListener(ControllerListener cl)
        {
            lst.remove(cl);
        }
        public long getMediaNanoseconds()
        {
            return (running ? System.currentTimeMillis() - time : time) * 1000000;
        }
        public int getState()
        {
            return (started ? 600 : 500);
        }
    }
}
