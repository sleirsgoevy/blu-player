package javax.media;

public class Manager
{
    public static Player createPlayer(MediaLocator ml)
    {
        return new Player.PlayerImpl();
    }
}
