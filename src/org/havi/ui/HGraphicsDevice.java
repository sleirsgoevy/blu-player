package org.havi.ui;

import org.davic.resources.ResourceClient;
import java.awt.Dimension;

public class HGraphicsDevice
{
    public boolean reserveDevice(ResourceClient cli)
    {
        return true;
    }
    public HGraphicsConfiguration getBestConfiguration(HGraphicsConfigTemplate param)
    {
        return new HGraphicsConfiguration(param.dim);
    }
    public boolean setGraphicsConfiguration(HGraphicsConfiguration config)
    {
        HSceneFactory.getInstance().getDefaultHScene().setSize(config.dim.width, config.dim.height);
        return true;
    }
}
