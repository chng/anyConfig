package me.anyconfig.client;

import java.lang.Boolean;
import java.lang.String;

public interface IRemoteConfigService {
  Boolean registerApp(String appid, String ipaddr);

  void initLocalConfig(String appid);
}
