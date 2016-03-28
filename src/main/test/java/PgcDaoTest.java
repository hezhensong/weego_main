import com.weego.main.dao.PgcDao;
import com.weego.main.model.Pgc;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-28.
 */
public class PgcDaoTest  extends BaseTest{
    @Autowired
    private PgcDao pgcDao;

    @Test
    public void testGetPgcByCityId() {

    }

    @Test
    public void testGetSpecifiedPgc() {
        String id = "56e3947560f7fef1690001af";
        Pgc pgc = pgcDao.getSpecifiedPgc(id);
        System.out.println(pgc);
    }

}
