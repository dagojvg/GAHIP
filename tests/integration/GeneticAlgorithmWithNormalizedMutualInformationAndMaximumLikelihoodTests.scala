package integration

import org.junit.Assert._
import org.junit.Test
import geneticAlgorithm.initialization.File
import geneticAlgorithm.settings.Settings
import geneticAlgorithm.fitness.NormalizedMutualInformation
import geneticAlgorithm.initialization.Initialization
import geneticAlgorithm.selection.Tournament
import geneticAlgorithm.operators.crossover.Crossover
import geneticAlgorithm.operators.mutation.Mutation
import geneticAlgorithm.evolver.Evolver
import geneticAlgorithm.GeneticAlgorithm

class GeneticAlgorithmWithNormalizedMutualInformationAndMaximumLikelihoodTests {

  @Test
  def runGeneticAlgorithm_whenReadingGenotypesFromFileAndUsingNormalizedMutualInformationAndMaximumLikelihood_returnsSolutions() = {
    
    val genotypeFilePath = "C:/Temp/Beta2_AR_Genotypes.txt"
    val haplotypeFilePath = "C:/Temp/Beta2_AR_Haplotypes.txt"
    val resultFilePath = "C:/Temp/result.txt"
      
    val file = new File
    
    val amountOfSolutions = 1000
    val nGenerations = 100
    val crossoverRate = 0.6
    val mutationRate = 0.1
    val kRounds = 5    
    
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
        
        file.extractContentAsStrings(haplotypeFilePath) match {
          case Right(haplotypesString) => {
            
            val fittest = result.individualSolutions.filter(ind => ind.fitness != -1).sortBy(ind => ind.fitness)
            val fittestSolution = result.resolveSolutions.filter(resolveSolution => resolveSolution.solutionID == fittest(0).id)
            
            var solutionIsExpected = false
            
            fittestSolution.foreach {resolveSolution => 
              solutionIsExpected = haplotypesString.contains(resolveSolution.haplotype.haplotype)
            
            }
            
            assertTrue(solutionIsExpected)
          }
          case _ =>
        }
        
      }
      case _ => 
    }
  }
}