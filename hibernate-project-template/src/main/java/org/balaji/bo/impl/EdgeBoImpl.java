package org.balaji.bo.impl;

import java.util.List;
import java.util.Map;

import org.balaji.dao.BaseObjectDao;
import org.balaji.dao.EdgeDao;
import org.balaji.hibernate.bo.EdgeBo;
import org.balaji.hibernate.model.Edge;
import org.balaji.hibernate.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of Edge Business Object (EdgeBo)
 * 
 * @author root
 *
 */
@Service("edgeBo")
public class EdgeBoImpl extends BaseObjectBoImpl<Edge> implements EdgeBo {

	@Autowired
	private EdgeDao edgeDao;

	@Override
	public boolean updateTopology(Map<Integer, Node> nodeMap, List<int[]> edges) {
		return edgeDao.updateTopology(nodeMap, edges);
	}

	@Override
	public BaseObjectDao<Edge> getDao() {
		return edgeDao;
	}

}
