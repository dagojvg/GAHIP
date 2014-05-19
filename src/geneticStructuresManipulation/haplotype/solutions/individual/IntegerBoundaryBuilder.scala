package geneticStructuresManipulation.haplotype.solutions.individual

import geneticStructures.Genotype

trait IntegerBoundaryBuilder {

  def createAStructureContainingIndividualSolutions(genotypes: Vector[Genotype], amountOfSolutions: Int): Vector[IndividualSolution] 
  
  def generateRandomIntegerBoundary(genotype: Genotype): Int
  
}