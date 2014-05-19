package geneticAlgorithm.operators.crossover

import scala.util.Random
import geneticAlgorithm.operators.Operator

object UniformCrossoverOperator extends Operator {
  
  private val random = new Random(System.currentTimeMillis)
  
  def get : Double = {
    
    random.nextDouble
  }
  
}