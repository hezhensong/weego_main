import com.weego.main.dao.ShoppingDao;
import com.weego.main.model.Shopping;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-28.
 */
public class ShoppongDaoTest extends BaseTest {

    @Autowired
    private ShoppingDao shoppingDao;

    @Test
    public void testGetShoppingById() {
        String shoppingId = "5327c2300e6a71a80f000003";
        Shopping shopping = shoppingDao.getShoppingById(shoppingId);
        System.out.println(shopping);
    }
}
