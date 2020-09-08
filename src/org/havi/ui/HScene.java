package org.havi.ui;

import java.awt.*;

public class HScene extends Frame
{
    public HScene()
    {
        setSize(1920, 1080);
    }
    public void repaint()
    {
        paint(getGraphics());
    }
}
