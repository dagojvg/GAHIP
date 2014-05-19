package geneticAlgorithm

import geneticAlgorithm.evolver.Evolver
import geneticAlgorithm.data.Data
import geneticStructuresManipulation.haplotype.HaplotypeBuilder
import geneticAlgorithm.settings.Settings 

class GeneticAlgorithm(val evolver: Evolver, val data: Data, settings: Settings) {

  def run(): Data = {
    
    var n = 0
    
    var nextData = data;  
    
    while(n < settings.nGenerations){
      
      val nextGeneration = evolver.run(nextData)
      
      val haplotypeBuilder = new HaplotypeBuilder          
      
      nextGeneration.map{ individual => 
        individual.fitness = -1
      }
      
      val nextResolveSolutions = haplotypeBuilder.createHaplotypes(data.genotypes, nextGeneration)
      
      nextData = new Data(data.genotypes, nextResolveSolutions, nextGeneration)
      
      n += 1
    }
    
    nextData.individualSolutions.map{ individualSolution => 
      individualSolution.fitness = evolver.fitness.calculate(nextData, individualSolution)
    }       
    
    nextData
  }
}
