package sleirsgoevy;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;

public class Blob
{
    private Blob(){}
    private static HashMap blobs = new HashMap();
    private static long counter = 1;
    public static URL registerBlob(byte[] data) throws Exception
    {
        String name;
        synchronized(blobs)
        {
            name = ""+(counter++);
        }
        blobs.put(name, data);
        return new URL("blob:"+name);
    }
    public static void deregisterBlob(URL url) throws Exception
    {
        blobs.remove(url.toString().substring(5));
    }
    public static URL registerSoundBDMV(byte[] data, int idx) throws Exception
    {
        int numInputs = data[45];
        if(idx < 0 || idx >= numInputs)
            return null;
        int offset = 0;
        for(int i = 0; i < 4; i++)
            offset = offset << 8 | ((int)data[48+10*idx+i])&255;
        int size = 0;
        for(int i = 0; i < 4; i++)
            size = size << 8 | ((int)data[52+10*idx+i])&255;
        boolean stereo = data[46+10*idx] == 0x31;
        offset += 46+10*numInputs;
        byte[] blob = new byte[size+44];
        blob[0] = (byte)'R';
        blob[1] = (byte)'I';
        blob[2] = (byte)'F';
        blob[3] = (byte)'F';
        for(int i = 0; i < 4; i++)
            blob[i+4] = (byte)((size+40) >>> (8*i));
        blob[8] = (byte)'W';
        blob[9] = (byte)'A';
        blob[10] = (byte)'V';
        blob[11] = (byte)'E';
        blob[12] = (byte)'f';
        blob[13] = (byte)'m';
        blob[14] = (byte)'t';
        blob[15] = (byte)' ';
        blob[16] = 16; // header size
        blob[20] = 1; // pcm
        blob[22] = (byte)(stereo?2:1); // #channels
        blob[24] = (byte)48000; // sample rate
        blob[25] = (byte)(48000 >> 8);
        int byteRate = 48000 * blob[22];
        blob[28] = (byte)byteRate; // byte rate
        blob[29] = (byte)(byteRate >> 8);
        blob[30] = (byte)(byteRate >> 16);
        blob[32] = (byte)(2 * blob[22]); // bytes per sample
        blob[34] = (byte)(16 * blob[22]); // bits per sample
        blob[36] = (byte)'d';
        blob[37] = (byte)'a';
        blob[38] = (byte)'t';
        blob[39] = (byte)'a';
        for(int i = 0; i < 4; i++)
            blob[i+40] = (byte)(size >>> (8*i));
        for(int i = 0; i < size; i++)
            blob[i+44] = data[(i^1)+offset];
        URL xyu = registerBlob(blob);
        return xyu;
    }
    static
    {
        URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory()
        {
            public URLStreamHandler createURLStreamHandler(String proto)
            {
                if(proto.equals("blob"))
                    return new URLStreamHandler()
                    {
                        public URLConnection openConnection(URL url)
                        {
                            return new URLConnection(url)
                            {
                                public void connect(){}
                                public InputStream getInputStream()
                                {
                                    final byte[] data = (byte[])blobs.get(url.toString().substring(5));
                                    return new InputStream()
                                    {
                                        private int idx = 0;
                                        public synchronized int read()
                                        {
                                            if(idx == data.length)
                                                return -1;
                                            return data[idx++];
                                        }
                                        public synchronized int read(byte[] buf, int off, int len)
                                        {
                                            if(len == 0)
                                                return 0;
                                            else if(idx == data.length)
                                                return -1;
                                            if(idx + len > data.length)
                                                len = data.length - idx;
                                            for(int i = 0; i < len; i++)
                                                buf[off+i] = data[idx++];
                                            return len;
                                        }
                                        public int read(byte[] buf)
                                        {
                                            return read(buf, 0, buf.length);
                                        }
                                    };
                                }
                            };
                        }
                    };
                return null;
            }
        });
    }
}
