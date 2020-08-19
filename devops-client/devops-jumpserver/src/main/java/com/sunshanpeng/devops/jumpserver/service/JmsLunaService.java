package com.sunshanpeng.devops.jumpserver.service;


import com.sunshanpeng.devops.jumpserver.base.ApiConstants;
import com.sunshanpeng.devops.jumpserver.base.ApiType;
import com.sunshanpeng.devops.jumpserver.base.JmsException;
import com.sunshanpeng.devops.jumpserver.model.Luna;
import com.sunshanpeng.devops.jumpserver.request.JmsRequest;

import java.util.HashMap;
import java.util.Map;

public class JmsLunaService extends JmsBaseService {


    public JmsLunaService(String url, String username, String password) {
        super(url, username, password);
    }

    public JmsLunaService(String url, String token) {
        this.URL = url;
        this.TOKEN = token;
    }

    public Map<String, String> getLunaToken(Luna luna) {
        return super.add(luna, ApiConstants.LUNA_TOKEN);
    }

    public Map<String, String> validateToken(String token) {
        try {
            Map<String, String> map = new HashMap<>();
            map = JmsRequest.getRequest(this.URL + ApiConstants.LUNA_TOKEN_VALIDATE + token, null, ApiType.API_GET, this.TOKEN);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JmsException(e.getMessage());
        }
    }

    public Map<String, String> connectLunaLinux(String token) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("resultStr",this.URL + ApiConstants.LUNA_LINUX_CONNECT + token);
            map.put("code","200");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JmsException(e.getMessage());
        }
    }

    public Map<String, String> connectLunaWindows(String token) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("resultStr",this.URL + ApiConstants.LUNA_WINDOWS_CONNECT + token);
            map.put("code","200");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JmsException(e.getMessage());
        }
    }

}
