package org.dvb.ui;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class DVBBufferedImage extends BufferedImage
{
    public DVBBufferedImage(int w, int h)
    {
        super(w, h, BufferedImage.TYPE_INT_ARGB);
    }
    public DVBGraphics cruateGraphics()
    {
        return new DVBGraphics.DVBGraphicsImpl((Graphics2D)getGraphics());
    }
    public void dispose(){}
}
