import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weego.main.dao.AttractionDao;
import com.weego.main.dao.RestaurantDao;
import com.weego.main.model.Restaurant;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class AttractionDaoTest extends BaseTest {
    @Autowired
    private AttractionDao dao;

    @Autowired
    private RestaurantDao resDao;
    
    @Test
    public void testGetAttractionById() {
    
        List<Restaurant> restaurants = resDao.getRestaurantsByCityId("516a3519f8a6461636000003", "56f4a7ff654f7e1b14ca16f6", 1, 5);
        if(restaurants == null || restaurants.size() == 0) {
        	System.out.println("size 0");
        }
        
        for(int i=0;i<restaurants.size();i++) {
        	System.out.println(restaurants.get(i).getCityName());
        }
        System.out.println("finished");
    
    }
}
