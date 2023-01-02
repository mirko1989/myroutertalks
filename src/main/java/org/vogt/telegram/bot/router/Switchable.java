package org.vogt.telegram.bot.router;

public interface Switchable {

    public void on();

    public void off();

    public boolean isOn();

}
