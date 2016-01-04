package me.anyconfig.appconfig;

/**
 * Created by OurEDA on 2016/1/4.
 */
public class ConfigNotStaticException extends Exception{
    public static final ConfigNotStaticException instance = new ConfigNotStaticException();

    public ConfigNotStaticException() {
        super();
    }

}
