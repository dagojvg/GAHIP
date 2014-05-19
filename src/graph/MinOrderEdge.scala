package graph

object MinOrderEdge extends Ordering[Edge]{

  def compare(edge1: Edge, edge2: Edge) = edge2.weight.compare(edge1.weight)
}