package geneticStructuresManipulation.haplotype.solutions.individual

import scala.util.Random
import geneticStructures.Genotype

class IndividualSolutionBuilder extends IntegerBoundaryBuilder {
    
  private var individualSolutionsStructure: Vector[IndividualSolution] = Vector.empty[IndividualSolution]
  private var random = new Random(System.currentTimeMillis)
  
  def individualSolutions = individualSolutionsStructure
  
  def createAStructureContainingIndividualSolutions(genotypes: Vector[Genotype], amountOfSolutions: Int): Vector[IndividualSolution] = {
    
    random = new Random(System.currentTimeMillis)
    
    val amountOfSol = Vector.range(0, amountOfSolutions)
    
    individualSolutionsStructure  =  (for(i<- amountOfSol)
               						 	yield new IndividualSolution(genotypes.map(genotype => generateRandomIntegerBoundary(genotype))))
               						 
    individualSolutionsStructure
  }
  
  def generateRandomIntegerBoundary(genotype: Genotype): Int = {    
   
    if(genotype.amountOfHeterozygousSites > 1 ){
      random.nextInt((scala.math.pow(2, genotype.amountOfHeterozygousSites - 1)) toInt)
    }
    else{
      0
    }
  }
}