package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;

/**
 * Created by Nikol_000 on 30-04-2016.
 */
public class Car
{
    BitmapCoordinates currentBitmap;
    int type;
    float x, y, velocity;
    double timeReset = 0.02, timeCount = timeReset;

    public Car(int type)
    {
        //currentBitmap = Random eller forudbestemt. evt "tilfældigt" med valgt biler i forskellige lanes
        // Bitmap with all cars, are in the Renderer class.
        this.type = type;


        if (type == 1)
        {
            // Lowest Lane -- Driving right
            currentBitmap = new BitmapCoordinates(0,0,130,48);
            x = 0 - currentBitmap.getWidth();
            y = 276;
            velocity = 1.5f;
        }
        else if (type == 2)
        {
            // Second Lowest Lane -- Driving Right
            currentBitmap = new BitmapCoordinates(0,50,130,48);
            x = 0 - currentBitmap.getWidth();
            y = 226;
            velocity = 3f;
        }
        else if (type == 3)
        {
            // Second "Highest" Lane -- Driving Left
            currentBitmap = new BitmapCoordinates(0,100,130,48);
            x = 710;
            y = 126;
            velocity = -3f;
        }
        else if (type == 4)
        {
            // "Highest" Lane -- Driving Left
            currentBitmap = new BitmapCoordinates(0,0,130,48);
            x = 710;
            y = 76;
            velocity = -1.5f;
        }
    }

    public void update(float deltaTime)
    {
        timeCount = timeCount - deltaTime;
        if (timeCount <= 0)
        {
            x = x + velocity;
            timeCount = timeReset;
        }
    }
}
