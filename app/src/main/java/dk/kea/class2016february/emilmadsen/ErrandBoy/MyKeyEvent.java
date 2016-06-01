package dk.kea.class2016february.emilmadsen.ErrandBoy;

public class MyKeyEvent
{
    public enum KeyEventType
    {
        Down,
        Up
    }

    public KeyEventType type;
    public int keyCode;
    public char character;
}
