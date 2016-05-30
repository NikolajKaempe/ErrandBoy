package dk.kea.class2016february.emilmadsen.ErrandBoy;

/**
 * Created by Nikol_000 on 07-04-2016.
 */
public class BitmapAction
{
    BitmapCoordinates[] bitmap;

    public BitmapAction(BitmapCoordinates[] bitmap) { this.bitmap = bitmap; }

    public BitmapCoordinates getBitmap(int i)
    {
        return bitmap[i];
    }
}


