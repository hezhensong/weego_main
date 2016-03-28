import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weego.main.dao.AttractionDao;
import com.weego.main.dao.RestaurantDao;
import com.weego.main.model.Attraction;
import com.weego.main.model.Restaurant;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class AttractionDaoTest extends BaseTest {
    @Autowired
    private AttractionDao dao;

    @Test
    public void testGetAttractionById() {
    
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
    
    }
}
