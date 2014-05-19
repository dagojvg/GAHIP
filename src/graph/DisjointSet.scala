package graph

class DisjointSet(val N: Int) {
  
  private var id = IndexedSeq.range(0, N)
  private var rank = IndexedSeq.fill(N)(0)
  private var _count = N
  
  def count = _count
  def predecessors = id
  
  def find(p: Int): Int = {
    
    var _p = p
    
    while(_p != id(_p)){
      id = id.updated(_p, id(id(_p)))
      
      _p = id(_p)
    }
    _p
  }
  
  def union(p: Int, q: Int) = {
    val i = find(p)
    val j = find(q)
    
    if(i != j){
      
      if(rank(i) < rank(j)){
        id = id.updated(i, j)
      } else if(rank(i) > rank(j)){
        id = id.updated(j, i)
      } else {
        id = id.updated(j, i)
        rank = rank.updated(i, rank(i) + 1)
      }
      
      _count -= 1
    } 
  }
}