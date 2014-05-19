package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolutionUniqueness
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution

class IndividualSolutionTests {

  @Test
  def id_afterAResolveSolutionIsCreated_returnsAUniqueIdentifierOfTheResolveSolution() = {
    
    val context = makeContext
    
    val stubIndividualSolutionUniqueness = context.mock(classOf[IndividualSolutionUniqueness])
    
    context.checking(
        new Expectations(){
          allowing(stubIndividualSolutionUniqueness).generateId()
          will(returnValue(1))
        })
        
   val individualSolutionValue = Vector(1)
   
   val individualSolution = new IndividualSolution(individualSolutionValue, stubIndividualSolutionUniqueness)
    
   assertEquals(1, individualSolution.id)
    
  }
  
  private def makeContext: Mockery = {
    new Mockery
  }
}