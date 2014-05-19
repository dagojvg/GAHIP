package geneticAlgorithm.fitness

import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.data.Data

trait Fitness {
  
  def calculate(data: Data, candidateSolution: IndividualSolution): Double
}