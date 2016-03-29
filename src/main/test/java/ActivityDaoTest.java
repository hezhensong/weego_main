import java.util.List;

import com.weego.main.dao.ActivityDao;
import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.model.Activity;
import com.weego.main.service.ActivityService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class ActivityDaoTest extends BaseTest {

    @Autowired
    private ActivityDao activityDao;
    
    @Autowired
    private ActivityService activityService;

    @Test
    public void testGetActivityById() {
        String id = "564ed6062e82a5f462000091";
        Activity activity = activityDao.getSpecifiedActivity(id);

        System.out.println(activity.toString());
    }
    
    @Test
    public void testservice() {
        String id = "564ed6062e82a5f462000091";
        ActivityDetailDto activity = activityService.getActivityDetail(id);

        System.out.println(activity.getId());
    }
    
    @Test
    public void testGetAllActivityBycityId() {
        String cityId = "516a34f958e3511036000001";
        List<Activity> list = activityDao.getAllActivity(cityId);

        System.out.println(list);
    }
}
