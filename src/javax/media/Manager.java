package javax.media;

import java.io.FileInputStream;
import java.io.IOException;

public class Manager
{
    private static byte[] readFile(String path) throws IOException
    {
        FileInputStream f = new FileInputStream(path);
        byte[] b = new byte[1];
        int off = 0;
        int l = 1;
        for(;;)
        {
            int chk = f.read(b, off, l);
            if(chk <= 0)
                break;
            off += chk;
            l -= chk;
            if(l == 0)
            {
                byte[] b2 = new byte[2 * off];
                for(int i = 0; i < off; i++)
                    b2[i] = b[i];
                b = b2;
                l = off;
            }
        }
        byte[] ans = new byte[off];
        for(int i = 0; i < off; i++)
            ans[i] = b[i];
        return ans;
    }
    public static Player createPlayer(MediaLocator ml) throws IOException
    {
        String url = ml.toString();
        if(url.substring(0, 7).equals("file://"))
            return new Player.AudioClipPlayerImpl(readFile(url.substring(7)), 0);
        else if(url.substring(0, 11).equals("bd://SOUND:"))
            return new Player.AudioClipPlayerImpl(readFile(System.getProperty("bluray.vfs.root")+"/BDMV/AUXDATA/sound.bdmv"), Integer.parseInt(url.substring(11), 16));
        else
        {
            System.err.println("createPlayer(): url "+url+" stubbed out");
            return new Player.PlayerImpl();
        }
    }
}
