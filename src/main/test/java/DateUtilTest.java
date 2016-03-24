import com.weego.main.util.DateUtil;
import org.joda.time.DateTimeZone;
import org.joda.time.tz.FixedDateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by liuniandxx on 16-3-18.
 */
public class DateUtilTest extends BaseTest {

    @Test
    public void testYYYYMMdd() {
        Date date = DateUtil.yyyyMMdd(new Date());
        System.out.println(date.toString());
    }

    @Test
    public void testYesterday() {
        Date date = DateUtil.yesterday(new Date());
        System.out.println(date.toString());
    }

    @Test
    public void testAfterNDays() {
        Date date = DateUtil.afterNDays(new Date(), -2);
        System.out.println(date);
    }

    @Test
    public void testFormatyyyyMMdd() {
        String today = DateUtil.formatyyyyMMdd(new Date());
        Assert.assertEquals(today, "20160318");
    }

    @Test
    public void testyyyyMMddtoDate() {
        Date today = DateUtil.yyyyMMddToDate("20160318");
        Assert.assertEquals(today, DateUtil.yyyyMMdd(new Date()));

    }

    @Test
    public void testFormatMMdd() {
        Assert.assertEquals("0320", DateUtil.formatMMdd(new Date()));
    }

    @Test
    public void testConvertGMTtoUTC() {
        Date today = DateUtil.yyyyMMddToDate("20160318");

        Date utc = DateUtil.covertTimeToUTC(today);
        System.out.println(utc.toString());
    }

    @Test
    public void testTimezone() {
        Date date3 = DateUtil.yyyyMMddToDate("20160324");
        System.out.println(date3.getTime());

        String timeZone = "GMT+7:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        TimeZone timeZone1 = TimeZone.getTimeZone(timeZone);
        format.setTimeZone(timeZone1);
        Date date1 = DateUtil.yyyyMMddToDate("20160324");
        Date date2 = null;
        try {
            date2 = format.parse("20160324");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date1.getTime());
        System.out.println(date2.getTime());

        System.out.println((date1.getTime() - date2.getTime()) / 3600000);
    }
 }
