package com.weego.main.service.impl;

import com.google.common.base.Strings;
import com.weego.main.dao.PolicyDao;
import com.weego.main.model.Policy;
import com.weego.main.service.PolicyService;
import com.weego.main.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-24.
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyDao policyDao;

    @Override
    public List<Policy> filterPolicyByDistance(List<Policy> policyList, String coordination) {
        if(policyList == null || policyList.size() <= 0) {
            return policyList;
        }

        List<Policy> matchPolicyList = new ArrayList<>();
        for (Policy policy : policyList) {
            if(Strings.isNullOrEmpty(policy.getCoordination()) || policy.getRadius() == null) {
                matchPolicyList.add(policy);
            } else {
                String policyCoordination = policy.getCoordination();
                if(isInDistanceRange(policyCoordination, coordination, policy.getRadius())) {
                    matchPolicyList.add(policy);
                }
            }
        }
        return matchPolicyList;
    }

    @Override
    public List<Policy> fiterPolicyByTimeRange(String cityId, String time) {
        return policyDao.getPolicyByTime(cityId, time);
    }

    private boolean isInDistanceRange(String localCoordination, String coordination, Integer radius) {
        if (!Strings.isNullOrEmpty(localCoordination) && !Strings.isNullOrEmpty(coordination)) {
            String[] localLatLon = localCoordination.split("[,，]");
            String[] latLon = coordination.split("[,，]");
            double distance = DistanceUtil.getDistance(localLatLon[1], localLatLon[0], latLon[1], latLon[0]) * 1000;
            if (distance <= radius) {
                return true;
            }
        }
        return false;
    }
}
