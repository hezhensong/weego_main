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
<<<<<<< HEAD
        String id = "516cc44ce3c6a60f69000012";
        Attraction attraction = dao.getAttractionById(id);
        System.out.println(attraction);

    	String cityId = "516a34f958e3511036000001";
    	String labelId = "5482821312c8ef7e21e2d181";
        List<Attraction> attractions = dao.getAttractionsByCityId(cityId, labelId, 1, 5);
        if(attractions == null || attractions.size() == 0) {
        	System.out.println("size 0");
        }
        
        for(int i=0;i<attractions.size();i++) {
        	System.out.println(attractions.get(i).getId());
        }
        System.out.println("finished");
=======
    
    	String id = "5322c08d2fab6f0c1d000030";
    	Restaurant restaurant = restaurantDao.getRestaurantById(id);
    	List<BasePOIOpenTime> openTimes = restaurant.getOpenTime();
    	
    	System.out.println(OpenTimeUtil.getOpenDesc(openTimes));
>>>>>>> 02c51a33ccb7aff6e49d9e5b858970dc229e8542
    
    }
}
