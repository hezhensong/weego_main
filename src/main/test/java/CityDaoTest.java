import com.weego.main.dao.CityDao;
import com.weego.main.model.City;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.StringTokenizer;

/**
 * Created by liuniandxx on 16-3-19.
 */
public class CityDaoTest extends BaseTest {
    @Autowired
    private CityDao dao;

    @Test
    public void testGetSpecifiedCity() {
        String cityId = "516a34f958e3511036000001";
        City city = dao.getSpecifiedCity(cityId);
        System.out.println(city.toString());
    }
}
