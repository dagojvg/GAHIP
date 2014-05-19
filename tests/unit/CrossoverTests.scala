package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.operators.Operator
import geneticAlgorithm.operators.crossover.Crossover

class CrossoverTests {

  @Test
  def performUniformCrossover_whenGivenTwoParentSolutions_returnAPairOfChildren() = {
    
    val context = makeContext
    
    val parent1 = new IndividualSolution(Vector(4, 2, 3))
    
    val parent2 = new IndividualSolution(Vector(7, 1, 8))
    
    val expectedResult = (new IndividualSolution(Vector(4, 1, 8)), new IndividualSolution(Vector(7, 2, 3)))
     
    val rate = 0.6
    
    val stubUniformCrossoverOperator = context.mock(classOf[Operator])
    
    context.checking(
        new Expectations(){
          one(stubUniformCrossoverOperator).get
          will(returnValue(0.1))
          
          one(stubUniformCrossoverOperator).get
          will(returnValue(0.7))
          
          one(stubUniformCrossoverOperator).get
          will(returnValue(0.8))
        })
        
    val crossover = new Crossover(rate, stubUniformCrossoverOperator)
    
    val result = crossover.perform(parent1, parent2)
    
    assertEquals(expectedResult, result)
    
  }
  
  private def makeContext: Mockery = {
    new Mockery
  }
}