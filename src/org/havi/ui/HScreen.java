package org.havi.ui;

public class HScreen
{
    private static HScreen instance = new HScreen();
    private HGraphicsDevice grdev;
    private HScreen()
    {
        grdev = new HGraphicsDevice();
    }
    public static HScreen getDefaultHScreen()
    {
        return instance;
    }
    public HGraphicsDevice getDefaultHGraphicsDevice()
    {
        return grdev;
    }
}
