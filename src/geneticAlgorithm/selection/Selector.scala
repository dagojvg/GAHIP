package geneticAlgorithm.selection

trait Selector {
  
  def get(populationLength: Int): Int
}