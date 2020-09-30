package com.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTToken {

    private static String signWith = "E:/project/EBPS/EBPS/src/main/java/com/config/JWTToken.java";
    private String userId, userName, userType, remoteUrl, userTypeColumn, actionStatus;
    private boolean status;
    Claims claims;
    List list;

    public JWTToken() {
    }

    public static String get(Map map) {
        String token = "";
        try {
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.HOUR, 8);
            token = Jwts.builder().
                    setClaims(map)
                    .setIssuedAt(date)
                    .setExpiration(c.getTime())
                    .signWith(SignatureAlgorithm.HS256, signWith).compact();
            
            System.out.println("c time " + c.getTime());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return token;

    }

    public JWTToken(String Authorization) {
        status = false;
        if (Authorization.length() > 100) {
            String token = Authorization.substring(7);
            try {
                claims = Jwts.parser().setSigningKey(signWith).parseClaimsJws(token).getBody();
                userId = claims.get("userCode").toString();
                userName = claims.get("userName").toString();
                userType = claims.get("userType").toString();
                remoteUrl = claims.get("remoteUrl").toString();
                status = true;
            } catch (Exception e) {
                userId = "";
                userName = "";
                userType = "";
                remoteUrl = "";
                status = false;
            }
        } else {
            System.out.println("invalid token");
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isValid() {
        return status;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getUserTypeName(String userType) {
        try {
            list = new com.fasterxml.jackson.databind.ObjectMapper().readValue(claims.get("userTypeAll").toString(), new com.fasterxml.jackson.core.type.TypeReference<List>() {
            });
            Map map;
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                if (map.get("userType").equals(userType)) {
                    userTypeColumn = map.get("column").toString();
                    actionStatus = map.get("actionStatus").toString();                    
                    return map.get("designation").toString();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "Invalid";
    }

    public String getUserTypeColumn() {
        return userTypeColumn;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public List getAllUserType() {
        return list;
    }

    

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"userType\":\"" + userType + "\",\"userName\":\"" + userName + "\"}";
    }

}
