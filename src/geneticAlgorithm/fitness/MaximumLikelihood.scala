package geneticAlgorithm.fitness

import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.data.Data


class MaximumLikelihood extends Fitness{
  
  def calculate(data: Data, candidateSolution: IndividualSolution): Double = {
    
    if(candidateSolution.fitness == -1){
      	    
	    candidateSolution.fitness = calculateHelper(data, candidateSolution)
    }
    
    candidateSolution.fitness
  }
  private def calculateHelper(data: Data, candidateSolution: IndividualSolution): Double = {
    
    var score = 0.0
	   
	val base2 = Math.log(2)
	
	val resolveSolution = data.resolveSolutions.filter(resolution => resolution.solutionID == candidateSolution.id)
	
	for(genotype<-data.genotypes){
	//for(i<-0 until data.genotypes.length){
	  
	  val pairOfIndividuals = resolveSolution.filter(solution => solution.refersToGenotype == genotype.id)
	  
	  score += Math.log(data.haplotypesFrequencies(pairOfIndividuals(0).haplotype) * data.haplotypesFrequencies(pairOfIndividuals(1).haplotype)) /
	           base2
	}
    
    -score
  }
}