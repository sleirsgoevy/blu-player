package org.davic.net;

public class Locator
{
    private String url;
    public Locator(String s)
    {
        url = s;
    }
    public Locator(String disc, int title, int playlist)
    {
        String u = "bd://";
        if(disc != null && !disc.equals(""))
        {
            u += disc;
            if(title >= 0)
                u += ".";
        }
        if(title >= 0)
        {
            u += Integer.toString(title, 16);
            if(playlist >= 0)
                u += ".";
        }
        if(playlist >= 0)
        {
            u += "PLAYLIST:";
            for(int i = 10000; i >= 1; i /= 10)
                u += Integer.toString(playlist / i % 16, 16);
        }
        url = u;
    }
    public String toString()
    {
        return url;
    }
}
