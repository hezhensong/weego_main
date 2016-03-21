import com.weego.main.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

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
}
