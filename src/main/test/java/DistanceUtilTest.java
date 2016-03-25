import com.weego.main.util.DistanceUtil;
import org.junit.Test;

/**
 * Created by liuniandxx on 16-3-25.
 */
public class DistanceUtilTest extends BaseTest {

    @Test
    public void testDistanceUtil() {
        String lng1 = "-73.963124";
        String lat1 = "40.81184";

        String lng2 = "-72.963124";
        String lat2 = "40.81184";
        System.out.println(DistanceUtil.getDistance(lat1, lng1, lat2, lng2));
    }
}
