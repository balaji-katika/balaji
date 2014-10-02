package org.balaji.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.balaji.dao.EdgeDao;
import org.balaji.db.transaction.MyDBTransaction;
import org.balaji.db.transaction.MyTransaction;
import org.balaji.hibernate.model.Edge;
import org.balaji.hibernate.model.Node;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of Edge DAO
 * 
 * @author root
 *
 */
@Repository("edgeDao")
public class EdgeDaoImpl extends BaseObjectDaoImpl<Edge> implements EdgeDao {
	private static Logger logger = Logger.getLogger(EdgeDaoImpl.class);

	/**
	 * Default constructor
	 */
	public EdgeDaoImpl() {
		super(Edge.class);
	}

	@Override
	public boolean updateTopology(final Map<Integer, Node> nodeMap,
			final List<int[]> edges) {

		MyTransaction<Boolean> myTransaction = new MyDBTransaction<Boolean>(
				getCurrentSession()) {

			@Override
			protected Boolean doSomething() {
				Boolean ret = new Boolean(true);
				Edge edge = null;
				for (int[] is : edges) {

					Node from = nodeMap.get(is[0]);
					Node to = nodeMap.get(is[1]);
					if (to == null || from == null) {
						logger.error("Invalid Edge found " + is[0] + " -> "
								+ is[1]);
						continue;
					}
					edge = findEdgeByNodes(from, to);
					if (edge == null) {
						edge = new Edge();
						edge.setFrom(from);
						edge.setTo(to);
						save(edge);
					}
				}
				return ret;
			}
		};

		myTransaction.execute();

		return myTransaction.getResult().booleanValue();
	}

	public Edge findEdgeByNodes(final Node from, final Node to) {
		MyTransaction<Edge> transaction = new MyDBTransaction<Edge>(
				getCurrentSession()) {

			@Override
			protected Edge doSomething() {
				Edge edge = null;
				@SuppressWarnings("rawtypes")
				List list = session.createQuery(
						"from Edge where node_from = " + from.getId() + " and node_to = "
								+ to.getId()).list();
				if (list.size() != 0) {
					return (Edge) list.get(0);
				}
				return edge;
			}
		};
		return transaction.executeReadOnly();
	}

}
