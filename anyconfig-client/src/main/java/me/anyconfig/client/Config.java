package me.anyconfig.client;

import me.anyconfig.appconfig.RegConfig;

/**
 * Created by OurEDA on 2016/1/4.
 */
public @interface Config {
    public String despription() default "";

    public RegConfig.Level level() default
            RegConfig.Level.Level2;

    public boolean persistant() default true;
}
