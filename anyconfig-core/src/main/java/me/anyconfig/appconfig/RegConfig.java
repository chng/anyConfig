package me.anyconfig.appconfig;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by OurEDA on 2015/12/31.
 */

public class RegConfig {

    @Id
    String id;

    @Column
    String name = "";
    @Column
    Serializable value = "";
    @Column
    String description = "";
    @Column
    Level level = Level.Level2;
    @Column
    Boolean isPersistant = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Serializable getValue() {
        return value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Boolean getIsPersistant() {
        return isPersistant;
    }

    public void setIsPersistant(Boolean isPersistant) {
        this.isPersistant = isPersistant;
    }

    enum Level {
        Level2, //一般功能级
        Level1, //核心功能级
        Level0; //系统级
    }
}
