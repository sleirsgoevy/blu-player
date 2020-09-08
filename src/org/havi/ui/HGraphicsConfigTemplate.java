package org.havi.ui;

import java.awt.Dimension;

public class HGraphicsConfigTemplate
{
    Dimension dim = null;
    public void setPreference(int a, Object b, int c)
    {
        if(a == 8)
            dim = (Dimension)b;
    }
}
