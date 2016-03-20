import com.weego.main.dao.AttractionDao;
import com.weego.main.model.Attraction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-20.
 */
public class AttractionDaoTest extends BaseTest {
    @Autowired
    private AttractionDao dao;

    @Test
    public void testGetAttractionById() {
        String id = "516cc44ce3c6a60f69000025";

        Attraction attraction = dao.getAttractionById(id);

        System.out.println(attraction.toString());
    }
}
