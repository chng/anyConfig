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
        // ��server��ȡ����
        return configList;
    }

    @GenerateInterface
    public void initLocalConfig(String appid) throws ConfigNotFoundException, ConfigNotStaticException {
        List<RegConfig> remoteRegConfig = getRemoteRegConfig(appid);
        try{
            // ����ÿһ��get����Զ��������
            for(RegConfig remote: remoteRegConfig) {
                // �ҵ�������
                Class clazz = Class.forName(remote.getClassName());
                if(!Modifier.isStatic(clazz.getModifiers())) {                 // ���������δ�����ɾ�̬�࣬���쳣
                    throw ConfigNotStaticException.instance;
                }
                // ���������ҵ�������
                final Field field = ReflectionUtils.findField(clazz, remote.getConfigName());
                // ͬ������
                // ������������ǳ־û���Զ��Ҳ�ǳ־û����Ա���Ϊ׼
                // ������������ǳ־û���Զ�̲��ǳ־û����Ա���Ϊ׼
                // ������ز��ǳ־û���Զ���ǳ־û�����Զ��Ϊ׼����֪ͨ��Ӧ��
                // ������ز��ǳ־û���Զ��Ҳ���ǳ־û����Ա���Ϊ׼
                final Config annotation = field.getAnnotation(Config.class);
                if(annotation.persistant()==false && remote.getIsPersistant()) {
                    field.set(null, remote.getConfigValue());
                    // TODO �˴���Ҫ��֤��ͨ��field��ֵ���Ƿ����ø��ֶε�set����
                    // ֪ͨ��app
                    LOG.warn(field.getName()+" is set to "+field.get(null)+" (by config server)");
                } else {
                    remote.setConfigValue(field.get(null));
                    // ���µ�config���͸�server
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
