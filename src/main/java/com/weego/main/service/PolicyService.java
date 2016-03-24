package com.weego.main.service;

import com.weego.main.model.Policy;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-24.
 */
public interface PolicyService {
    List<Policy> filterPolicyByDistance(List<Policy> policyList, String coordination);

    List<Policy> fiterPolicyByTimeRange(String cityId, String time);
}
