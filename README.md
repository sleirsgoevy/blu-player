# Blu-Player

This is a tool for playing [Blu-Play](https://blu-play.com/) games on a PC.

**Q. Why not just use a Blu-Ray player?**

Blu-Ray players are designed for viewing videos. They do not expect the user to heavily interact with the ontent, and often only provide rudimental controls. For example, VLC only lets you instantly press a button; there is no way to press and hold. Also for this reason PC players are not great at rendering dynamically generated graphics.

Unlike Blu-Ray players, Blu-Player is a tiny wrapper around Java AWT that only implements a subset BDJ-specific functionality. None of the "Blu-Ray" stuff is emulated except of BD-J.

**Q. Which Blu-Play games currently run?**

At the moment, 3 games have been tested:

* Ukkos Journey: Graphics works, but no sound as javax.media.Player is not implemented properly.
* The UFO game: ~~Boots to the main menu, but cannot start a game.~~ The game now starts, but with no background, as expected. The background is coming from a video and thus not shown.
* Doom 1993 port: Runs fine with most (but not all) sounds.

**Q. What can this be useful for?**

Currently I see this as a way for Blu-Play developers to test their games on PC without writing their own glue code. PowerDVD seems to be a viable alternative, but it is a paid closed-source app.

**Q. What has been implemented?**

* HScene: Implemented as a thin wrapper around java.awt.Frame.
* HGraphicsDevice: Only the "query & set video mode" operation is supported. Allows setting an arbitrary video mode by resizing the HScene window.
* HSound: Implemented as a wrapper around java.applet.AudioClip, which has a similar interface.
* javax.media.Player: `bd://SOUND:XX`-style URLs are implemented properly. Everything else is a stub.
* UserEventListener: Key presses and releases are properly passed through. Input keys are mapped to what BD-J sees using this table:

|Java key code |BD-J key code       |
|--------------|--------------------|
|87 (W)        |425 (Next track)    |
|83 (S)        |424 (Previous track)|
|65 (A)        |412 (Rewind)        |
|68 (D)        |417 (Fast forward)  |
|27 (Escape)   |19 (Pause)          |
|32 (Space)    |415 (Play)          |
|47 (/)        |461 (Popup menu)    |
|37-40 (arrows)|37-40               |
|48-57 (digits)|48-58               |
|10 (Enter)    |10                  |

**Q. What has not been implemented?**

1. Anything BluRay-related (video playback, titles, DVD registers, etc.)
2. Any kind of permission system (the program has full rights of the user invoking it)

You should test the game on an actual target device anyway, so I don't think this is much of a problem.

## Building

`bash build.sh`

## Running

`java -jar launcher.jar /path/to/bd/root path.of.the.Xlet`
