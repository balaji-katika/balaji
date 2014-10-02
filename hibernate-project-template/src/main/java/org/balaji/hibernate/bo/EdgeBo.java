package org.balaji.hibernate.bo;

import java.util.List;
import java.util.Map;

import org.balaji.hibernate.model.Edge;
import org.balaji.hibernate.model.Node;

/**
 * Business Object for Edge
 * @author root
 *
 */
public interface EdgeBo extends BaseObjectBo<Edge> {

	/**
	 * Update the topology information for the nodes
	 * @param nodeMap
	 * @param edges
	 */
	boolean updateTopology(Map<Integer, Node> nodeMap, List<int[]> edges);

}
