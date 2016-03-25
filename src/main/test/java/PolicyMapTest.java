import com.weego.main.dao.PolicyMapDao;
import com.weego.main.model.PolicyMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-25.
 */
public class PolicyMapTest extends BaseTest{

    @Autowired
    private PolicyMapDao policyMapDao;

    @Test
    public void testPolicyMap() {
        PolicyMap policyMap = policyMapDao.getPolicyMap("56f49e1b654f7e0944ac03bc", 5, "5694681508f1b79c24000007");
        System.out.println(policyMap);
    }
}
