package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.HaplotypeBuilder
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder
import geneticStructuresManipulation.haplotype.HaplotypeOperator
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution

class HaplotypeBuilderTests {
  
  @Test
  def createHaplotypes_forInitialSolutions_returnsAStructureContainingHaplotypes() = {
    
    val genotypes = Vector(new Genotype("10212202"))
    val amountOfSolutions = 1
    
    val context = makeContext    
    val stubIntegerBoundaryBuilder = context.mock(classOf[IntegerBoundaryBuilder])
    val expectedResult = Vector(
        new ResolveSolution(1, 3,new Haplotype("10010101"), genotypes(0).id), 
        new ResolveSolution(1, 3,new Haplotype("10111000"), genotypes(0).id))
    
    context.checking(
        new Expectations(){
          allowing (stubIntegerBoundaryBuilder).createAStructureContainingIndividualSolutions(`with`(any(classOf[Vector[Genotype]])), `with`(any(classOf[Int])))
          will(returnValue(Vector(new IndividualSolution(Vector(3)))))
        })
    val haplotypeBuilder = new HaplotypeBuilder(stubIntegerBoundaryBuilder)
    
    
    val result = haplotypeBuilder.createHaplotypes(genotypes, amountOfSolutions)
    
    assertTrue(expectedResult == result);
  }
  
  @Test
  def add_givenTwoHaplotypes_returnsConflatedGenotype() = {
    
    val haplotype1 = new Haplotype("1011001")
    
    val haplotype2 = new Haplotype("1101100")
    
    val expectedResult = new Genotype("1221202")
    
    val result = haplotype1 + haplotype2
    
    assertEquals(expectedResult, result)
  }
  
  @Test
  def createHaplotypes_havingThreeGenotypes_returnsSixHaplotypes() = {
    
    val genotypes = Vector(
    					new Genotype("10212202"),
    					new Genotype("10211100"),
    					new Genotype("22110102"))
    
    val amountOfSolutions = 1
    
    val haplotypeBuilder = new HaplotypeBuilder
    
    val result = haplotypeBuilder.createHaplotypes(genotypes, amountOfSolutions)
    
    assertEquals(6, result.length)
  }
  
  @Test
  def createHaplotypes_havingOneGenotypeWithNoHeterozygousSites_returnsTwoIdenticalHaplotypes() = {
    
    val genotypes = Vector(new Genotype("10011100"))
    
    val amountOfSolutions = 1
    
    val expectedResult = Vector(
        new ResolveSolution(1, 0, new Haplotype("10011100"), genotypes(0).id), 
        new ResolveSolution(2, 0, new Haplotype("10011100"), genotypes(0).id))
        
    val haplotypeBuilder = new HaplotypeBuilder
    
    val result = haplotypeBuilder.createHaplotypes(genotypes, amountOfSolutions)

    assertEquals(expectedResult, result)
  }
 
  @Test
  def createHaplotypes_forInitialSolutions_returnsAStructureContainingASpecifiedLengthOfRandomHaplotypes() = {
    
    val genotypes = Vector(
    					new Genotype("10212202"),
    					new Genotype("10211100"),
    					new Genotype("22110102"))
    
    val amountOfUniqueSolutions = 1
    val expectedAmount = 6
    
    val haplotypeBuilder = new HaplotypeBuilder
    
    val result = haplotypeBuilder.createHaplotypes(genotypes, amountOfUniqueSolutions)
    
    assertEquals(expectedAmount, result.length)
  }
  private def makeContext: Mockery = {
    new Mockery
  }

}