package org.havi.ui;

public class HSceneFactory
{
    private static HSceneFactory instance;
    private HScene scene;
    static
    {
        instance = new HSceneFactory();
    }
    private HSceneFactory(){}
    public static HSceneFactory getInstance()
    {
        return instance;
    }
    public synchronized HScene getDefaultHScene()
    {
        if(scene == null)
            scene = new HScene();
        return scene;
    }
    public HScene getFullScreenScene(HGraphicsDevice dev)
    {
        return getDefaultHScene();
    }
}
