package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;

/**
 * Created by Nikol_000 on 25-04-2016.
 */
public class Start extends Game
{
    @Override
    public Screen createStartScreen()
    {
        return new MainMenuScreen(this);
    }
}
