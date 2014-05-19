package graph

import scala.collection.mutable.PriorityQueue
import scala.collection.mutable.Queue

class KruskalMST {

  def calculate(graph: UpperTriangleUndirectedGraph):(Queue[Edge], IndexedSeq[Int]) = {
    
    calculateHelper(graph)
  }
  
  private def calculateHelper(graph: UpperTriangleUndirectedGraph):(Queue[Edge], IndexedSeq[Int]) = {
    
    val minEdge = buildMinEdge(graph)
    val vLen = graph.vertices.length
    val disjointSet = new DisjointSet(vLen)
    val minimalSpanningTree = new Queue[Edge]
    
    while(!minEdge.isEmpty && (minimalSpanningTree.length < vLen - 1)){
      
      val edge = minEdge.dequeue()
      
      val v = edge.vertexV
      val w = edge.vertexW
      
      if(disjointSet.find(v) != disjointSet.find(w)){
        disjointSet.union(v, w)
        minimalSpanningTree.enqueue(edge)
      }
    }
    
    (minimalSpanningTree, disjointSet.predecessors.distinct)
  }
  private def buildMinEdge(graph: UpperTriangleUndirectedGraph): PriorityQueue[Edge] = {
    
    var vInd = 0
    var vIndHelper = 1
    var minEdge = new PriorityQueue[Edge]()(MinOrderEdge)
    
    graph.graph.foreach{ vertex =>
    
      vertex.foreach{ weight => 
      
        if(weight != 0.0 && weight >= 0.5){
          minEdge.enqueue(new Edge(vIndHelper - 1, vInd + 1, weight))
        }
        vInd += 1
      }
      
      vInd = vIndHelper
      vIndHelper += 1
    }
    
    minEdge
  }
}