package geneticAlgorithm.operators.crossover

import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.operators.Operator


class Crossover(val rate: Double) {
  
  private var operator: Operator = UniformCrossoverOperator
  
  def this(rate: Double, operator: Operator){
    this(rate)
    
    this.operator = operator
  }
  
  def perform(parent1: IndividualSolution, parent2: IndividualSolution): (IndividualSolution, IndividualSolution) = {
    
    performHelper(parent1, parent2)
  }
  
  private def performHelper(parent1: IndividualSolution, parent2: IndividualSolution): (IndividualSolution, IndividualSolution) = {
    
    val cross = parent1.individualSolution zip parent2.individualSolution
    
    var offspring1: Vector[Int] =  Vector.empty
    var offspring2: Vector[Int] = Vector.empty
    
    val x = cross.map{
      case (p1, p2) => {
        
        val operatorRate = operator.get
        
        val c1 = if(operatorRate <= rate) p1 else p2
        val c2 = if(operatorRate > rate) p1 else p2
        
        offspring1 = offspring1 :+ c1
        offspring2 = offspring2 :+ c2
      }
    }
    
    (new IndividualSolution(offspring1), new IndividualSolution(offspring2))
  }
}