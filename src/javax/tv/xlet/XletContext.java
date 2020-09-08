package javax.tv.xlet;

public interface XletContext
{
    Object getXletProperty(String property);
    void notifyDestroyed();
    void notifyPaused();
    void resumeRequest();
}
