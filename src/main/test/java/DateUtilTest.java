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

}
