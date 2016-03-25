import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.service.RecommendHistoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-25.
 */

public class RecommendHistoryTest extends BaseTest {

    @Autowired
    private RecommendHistoryService recommendHistoryService;

    @Test
    public void testRecommendHistoryService() {
        String cityId = "516a34f958e3511036000001";
        String userId = "516a34f958e3511036000023";

        RecommendHistoryDto recommendHistoryDto = recommendHistoryService.getRecommendHistory(cityId, userId);
        System.out.println(recommendHistoryDto);
    }
}
