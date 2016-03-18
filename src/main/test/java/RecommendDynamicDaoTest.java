import com.mongodb.client.MongoDatabase;
import com.weego.main.dao.RecommendDynamicDao;
import com.weego.main.model.RecommendDynamic;
import com.weego.main.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-18.
 */
public class RecommendDynamicDaoTest extends BaseTest {

    @Autowired
    private RecommendDynamicDao dao;

    @Test
    public void testGetRecommendsByCityId() {
        String cityId = "516a34f958e3511036000001";
        List<RecommendDynamic> list = dao.getRecommendsByCityId(cityId);
        System.out.println(list.size());
    }

    @Test
    public void testGetRecomendsSpecifyDay() {
        Date date = DateUtil.yyyyMMddToDate("20170131");
        String cityId = "516a34f958e3511036000001";
        List<RecommendDynamic> list = dao.getRecomendsSpecifyDay(cityId, date);
        System.out.println(list.size());
    }
}
