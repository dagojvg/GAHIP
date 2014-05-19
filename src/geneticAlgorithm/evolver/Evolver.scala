package geneticAlgorithm.evolver

import geneticAlgorithm.selection.Tournament
import geneticAlgorithm.fitness.Fitness
import geneticAlgorithm.operators.crossover.Crossover
import geneticAlgorithm.operators.mutation.Mutation
import geneticAlgorithm.data.Data
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.settings.Settings

class Evolver(private val tournament: Tournament, val fitness: Fitness, private val crossover: Crossover, private val mutation: Mutation, private val settings: Settings) {

  def run(data: Data): Vector[IndividualSolution] = {
    
    var newGeneration: Set[IndividualSolution] = Set.empty
    
    while(newGeneration.size < settings.amountOfSolutions){
      
      val parent1 = tournament.run(data, settings.kRounds)
      
      val parent2 = tournament.run(data, settings.kRounds)
      
      val (crossOverOffspring1, crossOverOffspring2) = crossover.perform(parent1, parent2)
      
      val (mutationOffspring1, mutationOffspring2) = mutation.perform(data.genotypes, crossOverOffspring1, crossOverOffspring2)
      
      newGeneration = newGeneration + parent1 + parent2 + mutationOffspring1 + mutationOffspring2
    }
    
    newGeneration.toVector
  }
}