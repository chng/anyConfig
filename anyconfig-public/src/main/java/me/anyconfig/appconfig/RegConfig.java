package me.anyconfig.appconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by OurEDA on 2015/12/31.
 */

public class RegConfig {

    @Id
    String id;

    @Column
    String className = "";

    @Column
    String configName = ""; // 变量名

    @Column
    String configClass; // 变量类名，用于从json转成对象

    Object configValue = ""; //变量值，getConfigValue时通过json转换获得

    @Column(length=1000)
    String valueJson = ""; // 持久化的json。setConfigValue时设置


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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Object getConfigValue() {
        if(configValue==null) {
            try {
                configValue = JSON.parseObject(valueJson, Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return configValue;
    }

    public void setConfigValue(Object configValue) {
        this.configValue = configValue;
        valueJson = JSON.toJSONString(configValue);
    }

    public String getConfigClass() {
        return configClass;
    }

    public void setConfigClass(String configClass) {
        this.configClass = configClass;
    }

    public String getValueJson() {
        return valueJson;
    }

    public void setValueJson(String valueJson) {
        this.valueJson = valueJson;
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

    public enum Level {
        Level2, //一般功能级
        Level1, //核心功能级
        Level0; //系统级
    }
}
