package geneticAlgorithm.selection

import scala.util.Random

class RandomizedSelector extends Selector {
  
  private val random = new Random(System.currentTimeMillis)
  
  def get(populationLength: Int): Int = {
    
    random.nextInt(populationLength)
  }
}