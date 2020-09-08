import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.dvb.ui.DVBBufferedImage;
import org.dvb.event.EventManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.HashSet;
import java.net.URL;

import java.net.URLClassLoader;

public final class Launcher
{
    private Launcher(){}
    public static void main(String[] argv)
    {
        try
        {
            String iso_path = argv[0];
            String main_class = argv[1];
            if(System.getProperty("dvb.persistent.root") == null)
                System.setProperty("dvb.persistent.root", "./");
            System.setProperty("bluray.vfs.root", iso_path);
            final URLClassLoader clsldr = new URLClassLoader(new java.net.URL[]{new java.net.URL("jar:file://"+(new File(iso_path)).getAbsolutePath()+"/BDMV/JAR/00000.jar!/")});
            System.setSecurityManager(new SecurityManager()
            {
                public void checkExit(int status)
                {
                    throw new SecurityException("exitVM");
                }
                public void checkPermission(java.security.Permission p){}
            });
            Class main = clsldr.loadClass(main_class);
            Xlet x = (Xlet)main.newInstance();
            x.initXlet(new XletContext()
            {
                public Object getXletProperty(String prop)
                {
                    if(prop.equals("dvb.org.id") || prop.equals("dvb.app.id"))
                        return "";
                    return null;
                }
                public void notifyDestroyed(){}
                public void notifyPaused(){}
                public void resumeRequest(){}
            });
            x.startXlet();
            HScene hs = HSceneFactory.getInstance().getDefaultHScene();
            hs.setVisible(true);
            hs.toFront();
            hs.requestFocus();
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(hs);
            hs.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    try
                    {
                        x.pauseXlet();
                        x.destroyXlet(true);
                    }
                    catch(Throwable ex)
                    {
                        ex.printStackTrace();
                    }
                    System.setSecurityManager(null);
                    System.exit(0);
                }
            });
            final HashSet pressedKeys = new HashSet();
            hs.addKeyListener(new KeyListener()
            {
                public void keyPressed(KeyEvent e)
                {
                    Integer i_key = new Integer(e.getKeyCode());
                    if(!pressedKeys.contains(i_key))
                    {
                        pressedKeys.add(i_key);
                        EventManager._injectEvent(401, e.getKeyCode());
                    }
                }
                public void keyReleased(KeyEvent e)
                {
                    Integer i_key = new Integer(e.getKeyCode());
                    if(pressedKeys.contains(i_key))
                    {
                        pressedKeys.remove(i_key);
                        EventManager._injectEvent(402, e.getKeyCode());
                    }
                }
                public void keyTyped(KeyEvent e){}
            });
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
    }
}
