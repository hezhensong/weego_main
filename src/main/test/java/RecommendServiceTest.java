import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.service.RecommendInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-19.
 */
public class RecommendServiceTest extends BaseTest {

    @Autowired
    private RecommendInfoService recommendDynamicService;

    @Test
    public void testgetRecommendHistory() {
        String cityId = "516a34f958e3511036000001";
        RecommendHistoryDto dto = recommendDynamicService.getRecommendHistory(cityId);

        System.out.println(dto.toString());
    }
}
