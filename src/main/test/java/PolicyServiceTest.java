import com.weego.main.model.Policy;
import com.weego.main.service.PolicyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-25.
 */
public class PolicyServiceTest extends BaseTest{

    @Autowired
    private PolicyService policyService;

    @Test
    public void testPolicyService() {
        String cityId = "516a34f958e3511036000001";
        String time = "1457932600000";
<<<<<<< HEAD
        List<Policy> policyList = policyService.filterPolicyByTimeRange(cityId, time);
        System.out.println(policyList);
=======
//        List<Policy> policyList = policyService.fiterPolicyByTimeRange(cityId, time);
//        System.out.println(policyList);
>>>>>>> a63d4d227ea8f2e32268cc9d9a5cdde86dc653b6
    }

    @Test
    public void testPolicyServiceFilter() {
        String cityId = "516a34f958e3511036000001";
        String time = "1457932600000";
        List<Policy> policyList = policyService.filterPolicyByTimeRange(cityId, time);
        List<Policy> policyList1 = policyService.filterPolicyByDistance(policyList, "-72.963124,40.81184");
    }
}
