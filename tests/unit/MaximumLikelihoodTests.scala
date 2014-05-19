package unit

import org.junit.Assert._
import org.junit.Test
import geneticStructures.Genotype
import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticAlgorithm.fitness.MaximumLikelihood
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.data.Data

class MaximumLikelihoodFunctionTests {
  
  @Test
  def calculateMaximumLikelihood_whenGivenGenotypesAndPossibleSolution_returnsScoreForTheGivenSolution() = {
    
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
    
    
    val expectedResult = 15.509775004326936
    
    val delta = 1e-15
    
    val maximumLikelihood = new MaximumLikelihood
    
    val data = new Data(genotypes, resolveSolutions, individualSolutions)
    
    val result = maximumLikelihood.calculate(data, individualSolution)
    
    assertEquals(expectedResult, result, delta)
  }
}