package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;

/**
 * Created by Nikol_000 on 09-05-2016.
 */
public class ScrollingBackground
{

    Bitmap bitmap;
    BitmapCoordinates picture1, picture2;
    private final int MAX_X = (int)World.MAX_X + 10, MAX_Y= (int)World.MAX_Y + 25; // ignore "game walls"..
    private int x = 0;
    private float resetTime = 0.01f, timeCount = resetTime;


    public ScrollingBackground(Bitmap bitmap)
    {
        this.bitmap = bitmap;
        picture1 = new BitmapCoordinates(0,0,MAX_X,MAX_Y);
        picture2 = new BitmapCoordinates(0,0,1,MAX_Y);
    }

    public void move(float deltaTime)
    {
        timeCount = timeCount -deltaTime;
        if (timeCount <= 0)
        {
            x++;
            timeCount = resetTime;
            // Check = are picture1 gonna draw something from outside the bitmap??
            // Between 310 and 1030
            if (x > bitmap.getWidth() - (MAX_X))
            {
                // Check = are there anymore for picture1 to draw from the bitmap. x > 1030
                // if not then reset relevant fields.
                if (x >= bitmap.getWidth())
                {
                    x = 0;
                    picture1.srcX = 0;
                    picture1.width = MAX_X;
                    picture2.width = 1;
                }
                else
                {
                    picture1.srcX = x;
                    picture1.width = bitmap.getWidth() - x; // MAX_X to show first error (AND ONLY!!)

                    picture2.width = MAX_X - picture1.width;
                }
            }
            else
            {
                picture1.srcX = x;
            }

        }
    }
}
