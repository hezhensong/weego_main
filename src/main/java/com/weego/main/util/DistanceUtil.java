package com.weego.main.util;

import java.util.HashMap;
import java.util.Map;
  
public class DistanceUtil { 
        
    private static double EARTH_RADIUS = 6378.137; 
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
      
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lon1 用户经度
     * @param lat1 用户纬度
     * @param lon2 商家经度
     * @param lat2 商家纬度
     * @return
     */
    public static String getDistance(String lon1Str, String lat1Str, String lon2Str, String lat2Str) {
        Double lon1 = Double.parseDouble(lon1Str);
        Double lat1 = Double.parseDouble(lat1Str);
        Double lon2 = Double.parseDouble(lon2Str);
        Double lat2 = Double.parseDouble(lat2Str);
          
        double radlon1 = rad(lon1);
        double radlon2 = rad(lon2);
        double difference = radlon1 - radlon2;
        double mdifference = rad(lat1) - rad(lat2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radlon1) * Math.cos(radlon2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
            substring(0, distanceStr.indexOf("."));
          
        return distanceStr;
    }
      
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minlon
     * 最小经度 minlat
     * 最小纬度 maxlon
     * 最大经度 maxlat
     * 最大纬度 minlon
     */
    public static Map getAround(String lonStr, String latStr, String raidus) {
        Map map = new HashMap();
          
        Double lonitude = Double.parseDouble(lonStr);// 传值给经度
        Double longitude = Double.parseDouble(latStr);// 传值给纬度
  
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
          
        Double mpdlat = Double.parseDouble((degree * Math.cos(lonitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmlat = 1 / mpdlat;
        Double radiuslat = dpmlat * raidusMile;
        //获取最小经度
        Double minlon = longitude - radiuslat;
        // 获取最大经度
        Double maxlon = longitude + radiuslat;
          
        Double dpmlon = 1 / degree;
        Double radiuslon = dpmlon * raidusMile;
        // 获取最小纬度
        Double minlat = lonitude - radiuslon;
        // 获取最大纬度
        Double maxlat = lonitude + radiuslon;
          
        map.put("minlon", minlon+"");
        map.put("maxlon", maxlon+"");
        map.put("minlat", minlat+"");
        map.put("maxlat", maxlat+"");
          
        return map;
    }
      
    public static void main(String[] args) {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123
        System.out.println(getDistance("117.11811","36.68484","117.00999000000002","36.66123"));
          
        System.out.println(getAround("117.11811", "36.68484", "13000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
          
    }
      
}