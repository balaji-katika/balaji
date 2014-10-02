package org.balaji.bo.impl;

import org.balaji.dao.BaseObjectDao;
import org.balaji.dao.BundleDao;
import org.balaji.hibernate.bo.BundleBo;
import org.balaji.hibernate.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business object for Bundle
 * @author root
 *
 */
@Service("bundleBo")
public class BundleBoImpl extends BaseObjectBoImpl<Bundle> implements BundleBo {
	@Autowired
	private BundleDao bundleDao;
	@Override
	public BaseObjectDao<Bundle> getDao() {
		return bundleDao;
	}
}
