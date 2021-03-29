package com.itboyst.facedemo.base;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import static com.itboyst.facedemo.controller.ZcommandController.logger;


public class Iputil {

    public static String getClientIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String headerName = "x-forwarded-for";
        String ip = request.getHeader(headerName);
        if (null != ip && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个IP才是真实IP,它们按照英文逗号','分割
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (checkIp(ip)) {
            headerName = "Proxy-Client-IP";
            ip = request.getHeader(headerName);
        }
        if (checkIp(ip)) {
            headerName = "WL-Proxy-Client-IP";
            ip = request.getHeader(headerName);
        }
        if (checkIp(ip)) {
            headerName = "HTTP_CLIENT_IP";
            ip = request.getHeader(headerName);
        }
        if (checkIp(ip)) {
            headerName = "HTTP_X_FORWARDED_FOR";
            ip = request.getHeader(headerName);
        }
        if (checkIp(ip)) {
            headerName = "X-Real-IP";
            ip = request.getHeader(headerName);
        }
        if (checkIp(ip)) {
            headerName = "remote addr";
            ip = request.getRemoteAddr();
            // 127.0.0.1 ipv4, 0:0:0:0:0:0:0:1 ipv6
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        //logger.info("getClientIp  IP is " + ip + ", headerName = " + headerName);
        return ip;
    }
    private static boolean checkIp(String ip) {
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return true;
        }
        return false;
    }


    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static String SendGET(String url,String param){
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果

        try {
            //创建url
            URL realurl=new URL(url+"?"+param);
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
              //  System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
