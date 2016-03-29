import java.util.List;

import com.weego.main.model.Activity;
import com.weego.main.model.Attraction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weego.main.dao.RestaurantDao;
import com.weego.main.model.BasePOIOpenTime;
import com.weego.main.model.Restaurant;
import com.weego.main.util.OpenTimeUtil;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class AttractionDaoTest extends BaseTest {
    @Autowired
    private RestaurantDao restaurantDao;

    @Test
    public void testGetAttractionById() {

    	String id = "5322c08d2fab6f0c1d000030";
    	Restaurant restaurant = restaurantDao.getRestaurantById(id);
    	List<BasePOIOpenTime> openTimes = restaurant.getOpenTime();
    	
    	System.out.println(OpenTimeUtil.getOpenDesc(openTimes));

    }
}
