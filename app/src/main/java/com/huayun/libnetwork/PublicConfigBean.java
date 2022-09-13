package com.huayun.libnetwork;

/**
 * 公共配置信息
 */
public class PublicConfigBean {

    //首页活动配置
    private OpenActiveBean open_active;

    public OpenActiveBean getOpen_active() {
        return open_active;
    }

    public void setOpen_active(OpenActiveBean open_active) {
        this.open_active = open_active;
    }

    public static class OpenActiveBean {
        private String configKey;
        private String configName;
        private String configValue;

        public String getConfigKey() {
            return configKey;
        }

        public void setConfigKey(String configKey) {
            this.configKey = configKey;
        }

        public String getConfigName() {
            return configName;
        }

        public void setConfigName(String configName) {
            this.configName = configName;
        }

        public String getConfigValue() {
            return configValue;
        }

        public void setConfigValue(String configValue) {
            this.configValue = configValue;
        }
    }
}
