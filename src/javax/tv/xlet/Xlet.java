package javax.tv.xlet;

public interface Xlet
{
    void destroyXlet(boolean wtf) throws XletStateChangeException;
    void initXlet(XletContext ctxt) throws XletStateChangeException;
    void pauseXlet();
    void startXlet() throws XletStateChangeException;
}
