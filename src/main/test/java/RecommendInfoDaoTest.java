import com.weego.main.dao.RecommendInfoDao;
import com.weego.main.model.RecommendInfo;
import com.weego.main.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-18.
 */
public class RecommendInfoDaoTest extends BaseTest {

    @Autowired
    private RecommendInfoDao dao;

    @Test
    public void testGetRecommendsByCityId() {
        String cityId = "516a34f958e3511036000001";
        List<RecommendInfo> list = dao.getRecommendsByCityId(cityId);
        System.out.println(list.size());
    }

    @Test
    public void testGetRecomendsSpecifyDay() {
        Date date = DateUtil.yyyyMMddToDate("20160206");
        String cityId = "516a34f958e3511036000001";
        List<RecommendInfo> list = dao.getRecomendsSpecifyDay(cityId, date);
        System.out.println(list.size());
    }

    @Test
    public void testGetRecommendsAll() {

        List<RecommendInfo> list =  dao.getRecommendsAll();
        System.out.println(list.toArray());
    }
}
