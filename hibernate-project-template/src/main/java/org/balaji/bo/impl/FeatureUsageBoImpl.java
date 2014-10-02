package org.balaji.bo.impl;

import java.util.Map;

import org.balaji.dao.BaseObjectDao;
import org.balaji.dao.FeatureUsageDao;
import org.balaji.hibernate.bo.FeatureUsageBo;
import org.balaji.hibernate.model.Customer;
import org.balaji.hibernate.model.FeatureUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("featureUsageBo")
public class FeatureUsageBoImpl extends BaseObjectBoImpl<FeatureUsage> implements
		FeatureUsageBo {


	@Autowired
	private FeatureUsageDao featureUsageDao;

	@Override
	public Boolean updateMap(Map<String, Integer> usage, Customer custId) {
		// TODO Auto-generated method stub
		return featureUsageDao.updateMap(usage, custId);
	}

	@Override
	public BaseObjectDao<FeatureUsage> getDao() {
		// TODO Auto-generated method stub
		return featureUsageDao;
	}

}
