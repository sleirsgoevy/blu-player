package org.davic.media;

public class MediaLocator extends javax.media.MediaLocator
{
    public MediaLocator(org.davic.net.Locator l)
    {
        super(l.toString());
    }
}
