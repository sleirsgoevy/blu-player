package javax.media;

public class MediaLocator
{
    private String url;
    public MediaLocator(String s)
    {
        url = s;
    }
    public String toString()
    {
        return url;
    }
}
