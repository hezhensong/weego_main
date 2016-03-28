import com.weego.main.dto.PgcDetailDto;
import com.weego.main.service.PgcService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-21.
 */
public class PgcServiceTest extends BaseTest {

    @Autowired
    private PgcService pgcService;

    @Test
    public void testGetPgcPoiDetai() {
        String pgcId = "56e64d8960f7fef1690003c2";

        PgcDetailDto pgcDetailDto = pgcService.getPgcDetail(pgcId);

        System.out.println(pgcDetailDto.toString());
    }
}
