package org.verdictdb.core.execution;

import java.util.List;

import org.verdictdb.connection.DbmsConnection;
import org.verdictdb.core.query.SelectQuery;

public class AggExecutionNode extends CreateTableAsSelectExecutionNode {

  protected AggExecutionNode(String scratchpadSchemaName) {
    super(scratchpadSchemaName);
  }
  
  public static AggExecutionNode create(SelectQuery query, String scratchpadSchemaName) {
    AggExecutionNode node = new AggExecutionNode(scratchpadSchemaName);
    SubqueriesToDependentNodes.convertSubqueriesIntoDependentNodes(query, node);
    node.setSelectQuery(query);
    
    return node;
  }
  
  public SelectQuery getSelectQuery() {
    return selectQuery;
  }

//  /**
//   * Make this agg execution node perform progressive aggregation.
//   * 
//   * @param scrambleMeta
//   * @throws VerdictDbException 
//   */
//  public QueryExecutionNode toAsyncAgg(ScrambleMeta scrambleMeta) throws VerdictDbException {
//    QueryExecutionNode newNode = new AsyncAggExecutionNode(conn, scrambleMeta, resultSchemaName, resultTableName, query);
//    
//    // make that newNode runs only after the dependencies of this current node complete.
//    List<QueryExecutionNode> leaves = newNode.getLeafNodes();
//    for (QueryExecutionNode leaf : leaves) {
//      for (QueryExecutionNode dep : getDependents()) {
//        leaf.addDependency(dep);
//      }
//    }
//    return newNode;
//  }

  @Override
  public ExecutionInfoToken executeNode(DbmsConnection conn, List<ExecutionInfoToken> downstreamResults) {
    return super.executeNode(conn, downstreamResults);
  }

//  void generateDependency() throws VerdictDbException {
//    
//  }

}
