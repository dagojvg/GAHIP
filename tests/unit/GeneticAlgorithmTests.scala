package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder
import geneticAlgorithm.selection.Selector
import geneticAlgorithm.operators.Operator
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticStructuresManipulation.haplotype.HaplotypeBuilder
import geneticAlgorithm.fitness.MaximumLikelihood
import geneticAlgorithm.selection.Tournament
import geneticAlgorithm.data.Data
import geneticAlgorithm.operators.crossover.Crossover
import geneticAlgorithm.operators.mutation.Mutation
import geneticAlgorithm.evolver.Evolver
import geneticAlgorithm.GeneticAlgorithm
import geneticAlgorithm.settings.Settings

class GeneticAlgorithmTests {

  @Test
  def runGeneticAlgorithm_whenTheGenotypesAreRead_returnsSecondGeneration()={
    
    val context = makeContext
    
    val genotypes = Vector(
                    new Genotype("10212202"),
                    new Genotype("02210121"),
                    new Genotype("21202221"))
   
    val stubIntegerBoundaryBuilder = context.mock(classOf[IntegerBoundaryBuilder])
    val stubSelector = context.mock(classOf[Selector])
    val stubUniformOperator = context.mock(classOf[Operator])
    
    val individualSolutions = Vector(
                         new IndividualSolution(Vector(4, 2, 9)),
                         new IndividualSolution(Vector(3, 1, 8)),
                         new IndividualSolution(Vector(5, 0, 7)))
                         
    context.checking(
        new Expectations(){
          
          one (stubIntegerBoundaryBuilder).createAStructureContainingIndividualSolutions(`with`(any(classOf[Vector[Genotype]])), `with`(any(classOf[Int])))
          will(returnValue(individualSolutions))
          
          allowing(stubSelector).get(`with`(any(classOf[Int])))
          will(returnValue(1))
          
          one(stubUniformOperator).get
          will(returnValue(0.1))
          
          one(stubUniformOperator).get
          will(returnValue(0.7))
          
          one(stubUniformOperator).get
          will(returnValue(0.8))
          
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.1))
          
          one(stubIntegerBoundaryBuilder).generateRandomIntegerBoundary(`with`(any(classOf[Genotype])))
          will(returnValue(0))
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.1))
          
          one(stubIntegerBoundaryBuilder).generateRandomIntegerBoundary(`with`(any(classOf[Genotype])))
          will(returnValue(6))
    })
        
    val haplotypeBuilder = new HaplotypeBuilder(stubIntegerBoundaryBuilder)
    val amountOfSolutions = 3
    val resolveSolution = haplotypeBuilder.createHaplotypes(genotypes, amountOfSolutions)
     
    val fitness = new MaximumLikelihood
    
    val tournament = new Tournament(fitness, stubSelector)
    val data = new Data(genotypes, resolveSolution, haplotypeBuilder.individualSolutions)
    
    val nGenerations = 1
    val crossoverRate = 0.6
    val mutationRate = 0.1
    val kRounds = 5
    
    val settings = new Settings(amountOfSolutions, nGenerations, crossoverRate, mutationRate, kRounds)
    
    val crossover = new Crossover(settings.crossoverRate, stubUniformOperator)
          
    val mutation = new Mutation(settings.mutationRate, stubUniformOperator, stubIntegerBoundaryBuilder)       
    
    val evolver = new Evolver(tournament, fitness, crossover, mutation, settings)
    
    val expectedResult = Vector(
    					new IndividualSolution(Vector(3,1,8)),
    					new IndividualSolution(Vector(3,0,8)),
    					new IndividualSolution(Vector(3,1,6))
    )
    
    val geneticAlgorithm = new GeneticAlgorithm(evolver, data, settings)
     
    val result = geneticAlgorithm.run()
        
    assertEquals(expectedResult, result.individualSolutions)
  }
  
  @Test
  def runGeneticAlgorithm_whenTheGenotypesAreRead_returnsSecondGenerationHavingTheFitnessCalculatedForAllSolutions()={
    
    val context = makeContext
    
    val genotypes = Vector(
                    new Genotype("10212202"))
   
    val stubIntegerBoundaryBuilder = context.mock(classOf[IntegerBoundaryBuilder])
    val stubSelector = context.mock(classOf[Selector])
    val stubUniformOperator = context.mock(classOf[Operator])
    
    val individualSolutions = Vector(
                         new IndividualSolution(Vector(3, 1, 8)))
                         
    context.checking(
        new Expectations(){
          
          one (stubIntegerBoundaryBuilder).createAStructureContainingIndividualSolutions(`with`(any(classOf[Vector[Genotype]])), `with`(any(classOf[Int])))
          will(returnValue(individualSolutions))
          
          allowing(stubSelector).get(`with`(any(classOf[Int])))
          will(returnValue(0))
          
          one(stubUniformOperator).get
          will(returnValue(0.1))
          
          one(stubUniformOperator).get
          will(returnValue(0.7))
          
          one(stubUniformOperator).get
          will(returnValue(0.8))
          
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.1))
          
          one(stubIntegerBoundaryBuilder).generateRandomIntegerBoundary(`with`(any(classOf[Genotype])))
          will(returnValue(0))
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.2))
          
          one(stubUniformOperator).get
          will(returnValue(0.1))
          
          one(stubIntegerBoundaryBuilder).generateRandomIntegerBoundary(`with`(any(classOf[Genotype])))
          will(returnValue(6))
    })
        
    val haplotypeBuilder = new HaplotypeBuilder(stubIntegerBoundaryBuilder)
    val amountOfSolutions = 1
    val resolveSolution = haplotypeBuilder.createHaplotypes(genotypes, amountOfSolutions)
     
    val fitness = new MaximumLikelihood
    
    val tournament = new Tournament(fitness, stubSelector)
    val data = new Data(genotypes, resolveSolution, haplotypeBuilder.individualSolutions)
    
    val nGenerations = 1
    val crossoverRate = 0.6
    val mutationRate = 0.1
    val kRounds = 5
    
    val settings = new Settings(amountOfSolutions, nGenerations, crossoverRate, mutationRate, kRounds);
    
    val crossover = new Crossover(settings.crossoverRate, stubUniformOperator)
    
    val mutation = new Mutation(settings.mutationRate, stubUniformOperator, stubIntegerBoundaryBuilder)       
    
    val evolver = new Evolver(tournament, fitness, crossover, mutation, settings)
   
    val geneticAlgorithm = new GeneticAlgorithm(evolver, data, settings)
     
    val result = geneticAlgorithm.run()
    
    assertNotEquals(0, result.individualSolutions(0).fitness)
    assertNotEquals(0, result.individualSolutions(1).fitness)
    assertNotEquals(0, result.individualSolutions(2).fitness)
  }
  private def makeContext: Mockery = {
    new Mockery
  }
}