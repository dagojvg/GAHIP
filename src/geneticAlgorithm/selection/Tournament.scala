package geneticAlgorithm.selection

import geneticAlgorithm.data.Data
import geneticAlgorithm.fitness.Fitness
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution

class Tournament(val fitness: Fitness){
  
  private var selector: Selector = new RandomizedSelector
  
  def this(fitness: Fitness, selector: Selector){
    this(fitness)
    
    this.selector = selector
  }
  def run(data: Data, kRounds: Int): IndividualSolution = {   
    
    val dataContent = data
    val population = dataContent.individualSolutions
    
    var bestIndividual: Option[IndividualSolution] = Option.empty[IndividualSolution]
    
    for(i<-0 until kRounds){
      
      val individual = population(selector.get(population.length))
      
      bestIndividual = determineBestIndividual(dataContent, individual, bestIndividual)
    }
    
    bestIndividual.get
  }
  
  private def determineBestIndividual(dataContent: Data, individual: IndividualSolution, 
                                      bestIndividual: Option[IndividualSolution]): Option[IndividualSolution] = {
    
    bestIndividual match {
        
				   case Some(bestInd) => nextRun(dataContent, individual, bestInd)
				        
				   case None => firstRun(dataContent, individual)
    }
  }
  private def firstRun(dataContent: Data, individual: IndividualSolution): Option[IndividualSolution] = {
    
    fitness.calculate(dataContent, individual)
    
    Some(individual)
  }
  
  private def nextRun(dataContent: Data, individual: IndividualSolution, bestIndividual: IndividualSolution): Option[IndividualSolution] = {
    
    if(fitness.calculate(dataContent, individual) < bestIndividual.fitness){
      
      Some(individual)
      
    } else{
      
      Some(bestIndividual)
      
    }
  }
}