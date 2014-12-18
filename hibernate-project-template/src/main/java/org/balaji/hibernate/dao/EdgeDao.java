package org.balaji.hibernate.dao;

import java.util.List;
import java.util.Map;

import org.balaji.hibernate.model.Edge;
import org.balaji.hibernate.model.Node;

/**
 * DAO for Edge
 * @author root
 *
 */
public interface EdgeDao extends BaseObjectDao<Edge> {

	/**
	 * Update the topology information in the persistent store
	 * @param nodeMap
	 * @param edges
	 * @return
	 */
	public boolean updateTopology(Map<Integer, Node> nodeMap, List<int[]> edges);

}
