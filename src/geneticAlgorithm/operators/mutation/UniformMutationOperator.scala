package geneticAlgorithm.operators.mutation

import geneticAlgorithm.operators.Operator
import scala.util.Random

object UniformMutationOperator extends Operator {

  private val random = new Random(System.currentTimeMillis)
  
  def get : Double = {
    
    random.nextDouble
  }
}