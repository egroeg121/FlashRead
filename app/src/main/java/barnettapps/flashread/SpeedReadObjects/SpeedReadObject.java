package barnettapps.flashread.SpeedReadObjects;

import android.media.JetPlayer;

public abstract class SpeedReadObject{
    protected long Time;
    protected boolean Transparent;
    protected int ObjectLength;
    protected int CharLength;

    public SpeedReadObject() {
        Time = 0;
        Transparent = false;
        ObjectLength = 0;
        CharLength =0;
    }

    abstract int getObjectLength();
    abstract int getCharLength();
    abstract long getTime();
    abstract boolean getTransparent();

    abstract SpeedReadObject split(String splitter);

    @Override
    public boolean equals(Object o) {
        return true;
    }
}

