package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;

public class Car
{
    BitmapCoordinates currentBitmap;
    int type;
    float x, y, xVelocity;
    static final float VELOCITY = 1f;
    double timeReset = 0.02, timeCount = timeReset;
    List<BitmapCoordinates> group1Left, group1Right, group2Left, group2Right, group3Left, group3Right;
    {
        group1Right = new ArrayList<>();
        group1Right.add(new BitmapCoordinates(214,21,75,38));
        group1Right.add(new BitmapCoordinates(212,65,75,38));
        group1Right.add(new BitmapCoordinates(212,110,75,38));
        group1Right.add(new BitmapCoordinates(211,154,75,38));
        group1Right.add(new BitmapCoordinates(296,20,89,43));
        group1Right.add(new BitmapCoordinates(295,68,89,43));
        group1Right.add(new BitmapCoordinates(295,115,89,43));
        group1Right.add(new BitmapCoordinates(294,163,89,43));

        group2Right = new ArrayList<>();
        group2Right.add(new BitmapCoordinates(12,20,97,45));
        group2Right.add(new BitmapCoordinates(110,20,97,45));
        group2Right.add(new BitmapCoordinates(11,66,94,45));
        group2Right.add(new BitmapCoordinates(109,66,97,45));
        group2Right.add(new BitmapCoordinates(11,112,97,45));
        group2Right.add(new BitmapCoordinates(108,113,97,45));
        group2Right.add(new BitmapCoordinates(11,160,97,45));
        group2Right.add(new BitmapCoordinates(108,161,97,45));

        group3Right = new ArrayList<>();
        group3Right.add(new BitmapCoordinates(388,67,98,43));
        group3Right.add(new BitmapCoordinates(387,119,100,42));

        group1Left = new ArrayList<>();
        group1Left.add(new BitmapCoordinates(208,230,75,38));
        group1Left.add(new BitmapCoordinates(210,274,75,38));
        group1Left.add(new BitmapCoordinates(210,319,75,38));
        group1Left.add(new BitmapCoordinates(211,363,75,38));
        group1Left.add(new BitmapCoordinates(288,228,89,42));
        group1Left.add(new BitmapCoordinates(289,275,89,43));
        group1Left.add(new BitmapCoordinates(289,322,89,43));
        group1Left.add(new BitmapCoordinates(290,370,89,43));

        group2Left = new ArrayList<>();
        group2Left.add(new BitmapCoordinates(4,227,96,45));
        group2Left.add(new BitmapCoordinates(105,226,97,45));
        group2Left.add(new BitmapCoordinates(3,275,97,45));
        group2Left.add(new BitmapCoordinates(106,272,97,45));
        group2Left.add(new BitmapCoordinates(3,321,97,45));
        group2Left.add(new BitmapCoordinates(107,319,96,45));
        group2Left.add(new BitmapCoordinates(3,369,97,45));
        group2Left.add(new BitmapCoordinates(106,367,97,45));

        group3Left = new ArrayList<>();
        group3Left.add(new BitmapCoordinates(388,276,99,43));
        group3Left.add(new BitmapCoordinates(387,327,101,43));
    }

    public Car(int type, int niveau)
    {
        //currentBitmap = Random eller forudbestemt. evt "tilfældigt" med valgt biler i forskellige lanes
        // Bitmap with all cars, are in the Renderer class.
        this.type = type;
        int rand;

        if (niveau == 1)
        {
            rand = (int)(Math.random() * group1Right.size());
        }else if (niveau == 2)
        {
            rand = (int)(Math.random() * group2Right.size());
        }else
        {
            rand = (int)(Math.random() * group3Right.size());
        }

        if (type == 1)
        {
            // Lowest Lane -- Driving right
            if (niveau == 1)
            {
                currentBitmap = group1Right.get(rand);
            }else if (niveau == 2)
            {
                currentBitmap = group2Right.get(rand);
            }
            else
            {
                currentBitmap = group3Right.get(rand);
            }

            x = 0 - currentBitmap.width;
            y = 275 + ((50-currentBitmap.height+1) / 2);
            xVelocity = VELOCITY * (1 + (niveau * 0.1f));
        }
        else if (type == 2)
        {
            // Second Lowest Lane -- Driving Right
            if (niveau == 1)
            {
                currentBitmap = group1Right.get(rand);
            }else if (niveau == 2)
            {
                currentBitmap = group2Right.get(rand);
            }
            else
            {
                currentBitmap = group3Right.get(rand);
            }
            x = 0 - currentBitmap.width;
            y = 225 + ((50-currentBitmap.height+1) / 2);
            xVelocity = (2*VELOCITY) * (1 + (niveau * 0.1f));
        }
        else if (type == 3)
        {
            // Second "Highest" Lane -- Driving Left
            if (niveau == 1)
            {
                currentBitmap = group1Left.get(rand);
            }else if (niveau == 2)
            {
                currentBitmap = group2Left.get(rand);
            }
            else
            {
                currentBitmap = group3Left.get(rand);
            }
            x = World.MAX_X;
            y = 125 + ((50-currentBitmap.height+1) / 2);
            xVelocity = (-2*VELOCITY )* (1 + (niveau * 0.1f));
        }
        else if (type == 4)
        {
            // "Highest" Lane -- Driving Left
            if (niveau == 1)
            {
                currentBitmap = group1Left.get(rand);
            }else if (niveau == 2)
            {
                currentBitmap = group2Left.get(rand);
            }
            else
            {
                currentBitmap = group3Left.get(rand);
            }
            x = World.MAX_X;
            y = 75 + ((50-currentBitmap.height+1) / 2);
            xVelocity = -VELOCITY * (1 + (niveau * 0.1f));
        }
    }

    public void update(float deltaTime)
    {
        timeCount = timeCount - deltaTime;
        if (timeCount <= 0)
        {
            x = x + xVelocity;
            timeCount = timeReset;
        }
    }
}
