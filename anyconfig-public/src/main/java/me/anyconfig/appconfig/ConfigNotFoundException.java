package me.anyconfig.appconfig;

/**
 * Created by OurEDA on 2016/1/4.
 */
public class ConfigNotFoundException extends ClassNotFoundException {

    public static final ConfigNotFoundException instance = new ConfigNotFoundException();

    public ConfigNotFoundException() {
        super();
    }

}
