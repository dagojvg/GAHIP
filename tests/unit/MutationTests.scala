package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticStructures.Genotype
import geneticAlgorithm.operators.mutation.Mutation
import geneticAlgorithm.operators.Operator
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder

class MutationTests {
  
  @Test
  def performMutation_whenGivenTheOffsprings_returnMutatedOffspring() = {
    
    val context = makeContext
    
    val genotypes = Vector(
                    new Genotype("10212202"),
                    new Genotype("02210121"),
                    new Genotype("21202221"))
                    
    val offspring1 = new IndividualSolution(Vector(4, 1, 8))
    val offspring2 = new IndividualSolution(Vector(7, 2, 3))
    
    val expectedResult = (new IndividualSolution(Vector(4, 0, 8)), new IndividualSolution(Vector(7, 2, 1)))
    
    val stubUniformMutationOperator = context.mock(classOf[Operator])
    
    val stubIntegerBoundaryBuilder = context.mock(classOf[IntegerBoundaryBuilder])
    
    context.checking(
        new Expectations(){
          one(stubUniformMutationOperator).get
          will(returnValue(0.2))
          
          one(stubUniformMutationOperator).get
          will(returnValue(0.1))
          
          one(stubIntegerBoundaryBuilder).generateRandomIntegerBoundary(`with`(any(classOf[Genotype])))
          will(returnValue(0))
          
          one(stubUniformMutationOperator).get
          will(returnValue(0.2))
          
          one(stubUniformMutationOperator).get
          will(returnValue(0.2))
          
          one(stubUniformMutationOperator).get
          will(returnValue(0.2))
          
          one(stubUniformMutationOperator).get
          will(returnValue(0.1))
          
          one(stubIntegerBoundaryBuilder).generateRandomIntegerBoundary(`with`(any(classOf[Genotype])))
          will(returnValue(1))
        })
        
        
    val mutationRate = 0.1
    
    val mutation = new Mutation(mutationRate, stubUniformMutationOperator, stubIntegerBoundaryBuilder)
    
    val result = mutation.perform(genotypes, offspring1, offspring2)
    
    
    assertEquals(expectedResult, result)
  }
  private def makeContext: Mockery = {
    new Mockery
  }
}