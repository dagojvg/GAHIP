package unit

import org.junit.Assert._
import org.junit.Test
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructures.Haplotype
import geneticAlgorithm.data.Data
import geneticAlgorithm.fitness.NormalizedMutualInformation

class NormalizedMutualInformationTests {
  
  @Test
  def calculateNormalizedMutualInformation_whenGivenGenotypesAndPossibleSolution_returnsScoreForGivenSolution() = {
    val genotypes = Vector(
                    new Genotype("10212202"),
                    new Genotype("02210121"),
                    new Genotype("21202221"))
       
    val individualSolution = new IndividualSolution(Vector(4, 2, 9))
    
    val individualSolutions = Vector(individualSolution)
    
    val resolveSolutions = Vector(
    					   new ResolveSolution(individualSolution.id, 4, new Haplotype("10011000"), genotypes(0).id),
    					   new ResolveSolution(individualSolution.id, 4, new Haplotype("10110101"), genotypes(0).id),
    					   new ResolveSolution(individualSolution.id, 2, new Haplotype("00110101"), genotypes(1).id),
    					   new ResolveSolution(individualSolution.id, 2, new Haplotype("01010111"), genotypes(1).id),
    					   new ResolveSolution(individualSolution.id, 9, new Haplotype("01100011"), genotypes(2).id),
    					   new ResolveSolution(individualSolution.id, 9, new Haplotype("11001101"), genotypes(2).id))
    					   
    val data = new Data(genotypes, resolveSolutions, individualSolutions)
    
    val normalizedMutualInformation = new NormalizedMutualInformation
    
    val result = normalizedMutualInformation.calculate(data, individualSolution)
    
    val expectedResult = 25.20338438203127;
    
    val delta = 1E-15
    
    assertEquals(expectedResult, result, delta)
  }
}