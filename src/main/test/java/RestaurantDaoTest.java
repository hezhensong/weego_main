import com.weego.main.dao.RestaurantDao;
import com.weego.main.dto.BaseCardDto;
import com.weego.main.model.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class RestaurantDaoTest extends BaseTest{

    @Autowired
    private RestaurantDao dao;

    @Test
    public void testGetRestaurantById() {
        String id = "5322c08d2fab6f0c1d000010";
        Restaurant restaurant = dao.getRestaurantById(id);
        System.out.println(restaurant.toString());
    }
}
