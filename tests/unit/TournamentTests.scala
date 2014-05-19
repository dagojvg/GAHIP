package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructures.Genotype
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticStructuresManipulation.haplotype.HaplotypeBuilder
import geneticAlgorithm.selection.Tournament
import geneticAlgorithm.data.Data
import geneticAlgorithm.fitness.MaximumLikelihood
import geneticAlgorithm.selection.Selector

class TournamentTests {
  
  @Test
  def runTournament_whenGivenAPopulationOfSolutions_returnsTheFittestIndividual() = {
  
    val genotypes = Vector(
                    new Genotype("202222222000"),
                    new Genotype("100111101000"))
                    
    val amountOfSolutions = 3
  
    val context = makeContext    
    val stubIntegerBoundaryBuilder = context.mock(classOf[IntegerBoundaryBuilder])
    val stubSelector = context.mock(classOf[Selector])
    
    
    val returnVal = Vector(
                         new IndividualSolution(Vector(10, 0)),
                         new IndividualSolution(Vector(1, 0)),
                         new IndividualSolution(Vector(4, 0)))
    context.checking(
        new Expectations(){
          allowing (stubIntegerBoundaryBuilder).createAStructureContainingIndividualSolutions(`with`(any(classOf[Vector[Genotype]])), `with`(any(classOf[Int])))
          will(returnValue(returnVal))
          
          allowing(stubSelector).get(`with`(any(classOf[Int])))
          will(returnValue(1))
        })
        
    val haplotypeBuilder = new HaplotypeBuilder(stubIntegerBoundaryBuilder)
        
    val resolveSolution = haplotypeBuilder.createHaplotypes(genotypes, amountOfSolutions)
    
    val data = new Data(genotypes, resolveSolution, haplotypeBuilder.individualSolutions)
   
    val fitness = new MaximumLikelihood
    
    val tournament = new Tournament(fitness, stubSelector)
    
    val kRounds = 5
    
    val result = tournament.run(data, kRounds)
    
    val expectedResult = returnVal(1)
    
    assertEquals(expectedResult, result)
  }
  
  private def makeContext: Mockery = {
    new Mockery
  }
}