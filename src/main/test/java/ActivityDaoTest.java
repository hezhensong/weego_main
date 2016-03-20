import com.weego.main.dao.ActivityDao;
import com.weego.main.model.Activity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class ActivityDaoTest extends BaseTest {

    @Autowired
    private ActivityDao activityDao;

    @Test
    public void testGetActivityById() {
        String id = "566671487924fafc1d00017b";
        Activity activity = activityDao.getSpecifiedCity(id);

        System.out.println(activity.toString());
    }
}
