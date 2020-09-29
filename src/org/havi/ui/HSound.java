package org.havi.ui;

public class HSound
{
    private byte[] data;
    private java.applet.AudioClip player;
    private java.net.URL url;
    public HSound()
    {
        data = null;
    }
    public synchronized void set(byte[] data)
    {
        this.data = data;
    }
    public synchronized void play() throws Exception
    {
        if(url == null)
            url = sleirsgoevy.Blob.registerSoundBDMV(data, 0);
        player = java.applet.Applet.newAudioClip(url);
        player.play();
    }
    public synchronized void loop() throws Exception
    {
        if(player == null)
            play();
        player.loop();
    }
    public synchronized void stop() throws Exception
    {
        if(player != null)
        {
            player.stop();
            player = null;
            sleirsgoevy.Blob.deregisterBlob(url);
        }
    }
}
