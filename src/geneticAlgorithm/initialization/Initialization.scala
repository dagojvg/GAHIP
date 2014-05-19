package geneticAlgorithm.initialization

import geneticAlgorithm.data.Data
import geneticStructuresManipulation.genotype.GenotypeBuilder
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.HaplotypeBuilder
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.settings.Settings

class Initialization(val genotypesString: Vector[String], val settings: Settings) {
  private var dataHolder: Data = _
  
  def data = dataHolder
  
  def buildInitialSolutions(): Boolean = {
    
    val genotypeBuilder = new GenotypeBuilder
    val haplotypeBuilder = new HaplotypeBuilder
   
    val genotypesStructure = genotypeBuilder.createGenotypes(genotypesString)
    
    val resolveSolutionsStructure = haplotypeBuilder.createHaplotypes(genotypesStructure, settings.amountOfSolutions)
    
    val individualSolutionsStructure = haplotypeBuilder.individualSolutions
    
    if(genotypesStructure.length > 0 && resolveSolutionsStructure.length > 0 && individualSolutionsStructure.length > 0){
    
      dataHolder = new Data(genotypesStructure, resolveSolutionsStructure, individualSolutionsStructure)
      true
    }
    else{
      false
    }
  }
}