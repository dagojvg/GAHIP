package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolutionBuilder

class IndividualSolutionBuilderTests {
  
  @Test
  def createAStructureContainingIndividualSolutions_whenGivenASetOfGenotypes_returnsAStructureContainingIndividualSolutions()= {
    
    val genotypes = Vector(
    				new Genotype("10212202"),
    				new Genotype("01110110"))
    
    val amountOfSolutions = 2
    
    val context = makeContext
    
    val mockIntegerBoundaryBuilder = context.mock(classOf[IntegerBoundaryBuilder])
    
    val expectedResult = Vector(Vector(4, 0), Vector(2, 0))
        
    context.checking(
        new Expectations(){
          allowing (mockIntegerBoundaryBuilder).createAStructureContainingIndividualSolutions(
                                               `with`(any(classOf[Vector[Genotype]])), `with`(any(classOf[Int])))
          will(returnValue( Vector(Vector(4, 0), Vector(2, 0))))
        })
        
    val result = mockIntegerBoundaryBuilder.createAStructureContainingIndividualSolutions(genotypes, amountOfSolutions)
    
    assertEquals(expectedResult, result)
  }
  
  @Test
  def createAStructureContainingIndividualSolutions_whenGivenAsetOfGenotypes_returnsAStructureContainingASpecifiedLengthOfRandomIndividualSolutions()= {
    
    val genotypes = Vector(
    				new Genotype("10212202"),
    				new Genotype("22222110"))
    
    val amountOfSolutions = 10
    
    val individualSolutionBuilder = new IndividualSolutionBuilder
    
    val result = individualSolutionBuilder.createAStructureContainingIndividualSolutions(genotypes, amountOfSolutions)
    
    assertEquals(amountOfSolutions, result.length)
  }
  
  private def makeContext: Mockery = {
    new Mockery
  }
}