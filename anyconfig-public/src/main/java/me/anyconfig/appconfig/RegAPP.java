package me.anyconfig.appconfig;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by OurEDA on 2015/12/31.
 *
 * 一个RegAPP实例对应一个应用，管理多个RegConfig
 */
@Entity
public class RegAPP {

    @Id
    String appid;

    @Column
    String addr;

    @Column
    List<RegConfig> configList;

    @CreatedTimestamp
    Date gmtCreate;
    @UpdatedTimestamp
    Date gmtModify;


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<RegConfig> getConfigList() {
        return configList;
    }

    public void setConfigList(List<RegConfig> configList) {
        this.configList = configList;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
