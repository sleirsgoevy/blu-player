package javax.media;

import java.util.HashSet;
import java.util.Iterator;

public interface Player
{
    public void realize();
    public void prefetch();
    public void start() throws Exception;
    public void stop() throws Exception;
    public void close();
    public void addControllerListener(ControllerListener cl);
    public void removeControllerListener(ControllerListener cl);
    public long getMediaNanoseconds();
    public int getState();
    static class PlayerImpl implements Player
    {
        private static long STUB_MEDIA_DURATION = 1000000000000l;
        protected boolean running = false;
        protected boolean started = false;
        private long time = 0;
        public void realize(){}
        public void prefetch(){}
        private HashSet lst;
        public PlayerImpl()
        {
            lst = new HashSet();
        }
        protected long getDuration()
        {
            return STUB_MEDIA_DURATION;
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
                    broadcastEvent(new StartEvent());
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
                    try
                    {
                        self.stop();
                    }
                    catch(Exception e){}
                    self.time = 0;
                    broadcastEvent(new EndOfMediaEvent());
                }
            }
        }
        private synchronized void broadcastEvent(ControllerEvent e)
        {
            Iterator i = lst.iterator();
            while(i.hasNext())
            {
                ControllerListener cl = (ControllerListener)i.next();
                cl.controllerUpdate(e);
            }
        }
        private NotifierThread notifier;
        public synchronized void start() throws Exception
        {
            if(!running)
            {
                notifier = new NotifierThread(this, getDuration() - time);
                notifier.start();
                time = System.currentTimeMillis() - time;
                running = true;
                started = true;
            }
            else
                broadcastEvent(new StartEvent());
        }
        public synchronized void stop() throws Exception
        {
            if(running)
            {
                notifier.interrupt();
                notifier = null;
                time = System.currentTimeMillis() - time;
                running = false;
                broadcastEvent(new StopEvent());
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
    static class AudioClipPlayerImpl extends PlayerImpl
    {
        private java.net.URL url;
        private java.applet.AudioClip player;
        private byte[] data;
        private int idx;
        private long dur;
        public AudioClipPlayerImpl(byte[] d, int i)
        {
            super();
            data = d;
            idx = i;
            dur = sleirsgoevy.Blob.getSoundBDMVDuration(d, i);
        }
        protected long getDuration()
        {
            return dur;
        }
        public synchronized void start() throws Exception
        {
            if(!running)
            {
                if(url != null)
                    sleirsgoevy.Blob.deregisterBlob(url);
                url = sleirsgoevy.Blob.registerSoundBDMV(data, idx, getMediaNanoseconds());
                player = java.applet.Applet.newAudioClip(url);
                player.play();
            }
            super.start();
        }
        public synchronized void stop() throws Exception
        {
            if(running)
            {
                player.stop();
                sleirsgoevy.Blob.deregisterBlob(url);
                url = null;
                player = null;
            }
            super.stop();
        }
    }
}
