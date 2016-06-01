package dk.kea.class2016february.emilmadsen.ErrandBoy;

public class BitmapAction
{
    BitmapCoordinates[] bitmap;

    public BitmapAction(BitmapCoordinates[] bitmap) { this.bitmap = bitmap; }

    public BitmapCoordinates getBitmap(int i)
    {
        return bitmap[i];
    }
}


