package com.weego.main.util;


public class DistanceUtil {
private static double EARTH_RADIUS = 6378.137; 
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
    /**
     * @param lat1 第一个坐标的纬度
     * @param lon1 第一个坐标的经度
     * @param lat2 第二个坐标的纬度
     * @param lon2 第二个坐标的经度
     * @return
     */
    public static double GetDistance(double lat1, double lon1, double lat2, double lon2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    public static void main(String[] args) {

        System.out.println(GetDistance(39.9960970000,116.3640600000,39.9672990000,116.3650490000));
        System.out.println(GetDistance(36.68484,117.11811,36.66123,117.00999000000002));
    }
}
