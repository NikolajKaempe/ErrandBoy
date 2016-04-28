package dk.kea.class2016february.emilmadsen.ErrandBoy;

public class SimpleGame extends Game
{
    @Override
    public Screen createStartScreen()
    {
        return new SimpleScreen(this);
    }
}
