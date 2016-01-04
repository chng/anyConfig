package me.anyconfig.client;

import com.google.common.collect.Lists;
import me.anyconfig.appconfig.ConfigNotFoundException;
import me.anyconfig.appconfig.ConfigNotStaticException;
import me.anyconfig.appconfig.RegAPP;
import me.anyconfig.appconfig.RegConfig;
import me.generator.GenerateInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by OurEDA on 2016/1/1.
 */
public class RemoteConfigService {//implements IRemoteConfigService {

    private static final Log LOG = LogFactory.getLog(RemoteConfigService.class);

    @GenerateInterface
    public Boolean registerApp(String appid, String ipaddr) {
        RegAPP regAPP = new RegAPP();
        regAPP.setAppid(appid);
        regAPP.setAddr(ipaddr);
        //
        return true;
    }

    private List<RegConfig> getRemoteRegConfig(String appid) {
        List configList = Lists.newLinkedList();
        // 从server获取配置
        return configList;
    }

    @GenerateInterface
    public void initLocalConfig(String appid) throws ConfigNotFoundException, ConfigNotStaticException {
        List<RegConfig> remoteRegConfig = getRemoteRegConfig(appid);
        try{
            // 处理每一个get到的远程配置项
            for(RegConfig remote: remoteRegConfig) {
                // 找到配置类
                Class clazz = Class.forName(remote.getClassName());
                if(!Modifier.isStatic(clazz.getModifiers())) {                 // 如果配置类未声明成静态类，抛异常
                    throw ConfigNotStaticException.instance;
                }
                // 按变量名找到配置项
                final Field field = ReflectionUtils.findField(clazz, remote.getConfigName());
                // 同步规则：
                // 如果本地配置是持久化、远程也是持久化，以本地为准
                // 如果本地配置是持久化、远程不是持久化，以本地为准
                // 如果本地不是持久化、远程是持久化，以远程为准，并通知到应用
                // 如果本地不是持久化、远程也不是持久化，以本地为准
                final Config annotation = field.getAnnotation(Config.class);
                if(annotation.persistant()==false && remote.getIsPersistant()) {
                    field.set(null, remote.getConfigValue());
                    // TODO 此处需要验证：通过field赋值，是否会调用该字段的set方法
                    // 通知到app
                    LOG.warn(field.getName()+" is set to "+field.get(null)+" (by config server)");
                } else {
                    remote.setConfigValue(field.get(null));
                    // 把新的config发送给server
                    sendConfig(remote);
                }

            }
        } catch(ClassNotFoundException e) {
            throw ConfigNotFoundException.instance;
        } catch (IllegalAccessException e) {

        }
    }

    //TODO send config
    private void sendConfig(RegConfig remote) {

    }

}
