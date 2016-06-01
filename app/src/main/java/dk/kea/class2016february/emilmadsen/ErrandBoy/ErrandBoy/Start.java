package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;


public class Start extends Game
{
    @Override
    public Screen createStartScreen()
    {
        return new MainMenuScreen(this);
    }
}
