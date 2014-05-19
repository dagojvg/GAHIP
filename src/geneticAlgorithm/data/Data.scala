package geneticAlgorithm.data

import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticStructuresManipulation.haplotype.HaplotypesFrequencies

class Data(val genotypesStructure: Vector[Genotype], resolveSolutionsStructure: Vector[ResolveSolution], individualSolutionsStructure: Vector[IndividualSolution]) {
  
  private var genotypesStructure1 = genotypesStructure 
  private var resolveSolutionsStructure1 = resolveSolutionsStructure
  private var individualSolutionsStructure1 = individualSolutionsStructure
  private val haplotypesFrequenciesInstance = new HaplotypesFrequencies
  private var haplotypesFrequenciesStructure = haplotypesFrequenciesInstance.calculate(resolveSolutions)
  
  def genotypes = genotypesStructure1  
  
  def resolveSolutions = resolveSolutionsStructure1
  
  def individualSolutions = individualSolutionsStructure1
  
  def haplotypesFrequencies = haplotypesFrequenciesStructure
  
}