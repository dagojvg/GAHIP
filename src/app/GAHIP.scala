package app

import geneticAlgorithm.initialization.File
import geneticAlgorithm.settings.Settings
import geneticAlgorithm.initialization.Initialization
import geneticAlgorithm.fitness.NormalizedMutualInformation
import geneticAlgorithm.selection.Tournament
import geneticAlgorithm.operators.crossover.Crossover
import geneticAlgorithm.operators.mutation.Mutation
import geneticAlgorithm.evolver.Evolver
import geneticAlgorithm.GeneticAlgorithm

object GAHIP extends App {
  
	if(args.length == 2){
	  run(args(0), args(1))
	}
	else {
	  printMessage("USAGE:\tGenotypesFilePath\tResultFilePath")
	}
	private def printMessage(message: String) = {
	  System.out.println(message)
	}
	private def run(genotypeFilePath : String, resultFilePath: String){ 
	  
	  val file = new File
	   
	    val amountOfSolutions = 1000
	    val nGenerations = 100
	    val crossoverRate = 0.6
	    val mutationRate = 0.1
	    val kRounds = 5    
	    
	    printMessage("Initializing GAHIP...")
	    
	    val settings = new Settings(amountOfSolutions, nGenerations, crossoverRate, mutationRate, kRounds)
	    
	    file.extractContentAsStrings(genotypeFilePath) match {
	      case Right(genotypesString) => {
	        
	        val initialization = new Initialization(genotypesString, settings)
	    
	        initialization.buildInitialSolutions()
	        
	        val fitness = new NormalizedMutualInformation
	        
	        val tournament = new Tournament(fitness)
	        
	        val crossover = new Crossover(settings.crossoverRate)
	        
	        val mutation = new Mutation(settings.mutationRate)
	        
	        val evolver = new Evolver(tournament, fitness, crossover, mutation, settings)
	        
	        val geneticAlgorithm = new GeneticAlgorithm(evolver, initialization.data, settings)
	        
	        val result = geneticAlgorithm.run();
	        
	        val fittest = result.individualSolutions.filter(ind => ind.fitness != -1).sortBy(ind => ind.fitness)
            val fittestSolution = result.resolveSolutions.filter(resolveSolution => resolveSolution.solutionID == fittest(0).id)
            
            printMessage("Writing haplotypes to file")
	        file.printToFile(new java.io.File(resultFilePath))(p => {
	          fittestSolution.foreach{ hap => p.println(hap.haplotype.haplotype)}	       
	        })
	        printMessage("Finished!")
	      }
	      case _ =>
	    }
	}
}