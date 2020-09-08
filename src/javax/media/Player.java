package javax.media;

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
    class PlayerImpl implements Player
    {
        private boolean running = false;
        private boolean started = false;
        private long time = 0;
        public void realize(){}
        public void prefetch(){}
        public synchronized void start()
        {
            if(!running)
            {
                time = System.currentTimeMillis() - time;
                running = true;
                started = true;
            }
        }
        public synchronized void stop()
        {
            if(running)
            {
                time = System.currentTimeMillis() - time;
                running = false;
            }
        }
        public void close(){}
        public void addControllerListener(ControllerListener cl){}
        public void removeControllerListener(ControllerListener cl){}
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
