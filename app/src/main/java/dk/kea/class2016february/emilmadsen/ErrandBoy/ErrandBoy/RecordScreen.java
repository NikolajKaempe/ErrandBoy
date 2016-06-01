package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import java.util.ArrayList;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;

public class RecordScreen extends Screen
{
    private MainMenuScreen mainMenuScreen;
    private Bitmap background;
    private ArrayList<Statistic> statistics;

    public RecordScreen(Game game, MainMenuScreen mainMenuScreen)
    {
        super(game);
        this.mainMenuScreen = mainMenuScreen;
        //statistics = game.getGameStatisticObject();
        background = game.loadBitmap("recordBackground.png");
    }

    @Override
    public void update(float deltaTime)
    {
        if (game.isTouchDown(0))
        {
            // Top left corner
            if (game.getTouchX(0) <= 25 && game.getTouchY(0) <= 25)
            {
                game.setScreen(mainMenuScreen);
            }
        }

        game.drawBitmap(background,0,0);

        if (statistics.size() > 0)
        {
            if (statistics.size() >= 10)
            {
                for (int i = 0; i < 10;i++ )
                {
                    // TODO draw statistics for top 10;
                }
            }
            else
            {
                for (int i = 0; i < statistics.size();i++ )
                {
                    // TODO draw statistics from 1-amount
                }
            }
        }
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
