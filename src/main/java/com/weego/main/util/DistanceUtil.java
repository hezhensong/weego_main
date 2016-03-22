package com.weego.main.util;

public class DistanceUtil {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(String d) {
        return Double.valueOf(d) * Math.PI / 180.0;
    }

    /**
     * @param lat1
     *            第一个坐标的纬度
     * @param lng1
     *            第一个坐标的经度
     * @param lat2
     *            第二个坐标的纬度
     * @param lng2
     *            第二个坐标的经度
     * @return 返回距离是 km
     */
    public static double getDistance(String lat1, String lng1, String lat2, String lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    public static void main(String[] args) {

        // 济南国际会展中心经纬度：117.11811 36.68484
        // 趵突泉：117.00999000000002 36.66123

        // 北京科技大学 纬度 和 经度39.9960970000,116.3640600000
        // 北京邮电大学 纬度 和 经度 39.9672990000,116.3650490000

        // 117.01028712333508(Double), 117.22593287666493(Double),
        // 36.44829619896034(Double), 36.92138380103966(Double)
        System.out.println(getDistance("39.9960970000", "116.3640600000", "39.9672990000", "116.3650490000"));

        System.out.println(getDistance("36.68484", "117.11811", "36.66123", "117.00999000000002"));
    }
}