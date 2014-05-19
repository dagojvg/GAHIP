package graph

class UpperTriangleUndirectedGraph(val graph: IndexedSeq[IndexedSeq[Double]]) {
  
  private val _vertices: IndexedSeq[Int] = IndexedSeq.range(0, graph.length + 1)
  
  def vertices = _vertices
}