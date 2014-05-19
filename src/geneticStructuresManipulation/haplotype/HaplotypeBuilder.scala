package geneticStructuresManipulation.haplotype

import geneticStructures.Allele
import geneticStructures.Genotype
import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolutionBuilder
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder
import geneticStructuresManipulation.haplotype.solutions.partial.PartialSolutionGenerator
import geneticStructuresManipulation.haplotype.solutions.resolve.FinalSolutionGenerator
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution

class HaplotypeBuilder(){

  private var integerBoundaryBuilder: IntegerBoundaryBuilder = new IndividualSolutionBuilder
  private var partialSolutionGenerator = new PartialSolutionGenerator
  private var finalSolutionGenerator = new FinalSolutionGenerator
  private var individualSolutionsStructure: Vector[IndividualSolution] = Vector.empty[IndividualSolution]
  private var resolveSolutionsStructure:Vector[ResolveSolution] = Vector.empty[ResolveSolution]
  
  def individualSolutions = individualSolutionsStructure
  
  def resolveSolutions = resolveSolutionsStructure
  
  def this(integerBoundaryBuilder: IntegerBoundaryBuilder){
    this()
    
    this.integerBoundaryBuilder =  integerBoundaryBuilder
  }
  
  def createHaplotypes(genotypes: Vector[Genotype], individualSolutionsStructure: Vector[IndividualSolution]): Vector[ResolveSolution] ={
    
    createHaplotypesHelper(genotypes, individualSolutionsStructure)
  }
  def createHaplotypes(genotypes: Vector[Genotype], amountOfSolutions: Int): Vector[ResolveSolution] = {
    
    createHaplotypesHelper(genotypes, amountOfSolutions)
  }
  private def createHaplotypesHelper(genotypes: Vector[Genotype], individualSolutionsStructure: Vector[IndividualSolution]): Vector[ResolveSolution] = {
    
    
    individualSolutionsStructure.map { individualSolutionList =>
      
      val individualPerGenotypes = individualSolutionList.individualSolution zip genotypes
         
      individualPerGenotypes.map{ case (individualSolution, genotype) =>
        
        val (partialSolution1, partialSolution2) = generatePartialSolution(genotype, individualSolution)
           
        val (haplotype1, haplotype2) = generateFinalPairSolution(genotype, partialSolution1, partialSolution2)
           
        addHaplotypePairToStructure(individualSolutionList.id, individualSolution, haplotype1, haplotype2, genotype.id)
         
      }
     }
    
    resolveSolutionsStructure
  }
  private def createHaplotypesHelper(genotypes: Vector[Genotype], amountOfSolutions: Int): Vector[ResolveSolution] = {
    
    individualSolutionsStructure = generateIndividualSolutions(genotypes, amountOfSolutions)
    
    individualSolutionsStructure.map { individualSolutionList =>
      
      val individualPerGenotypes = individualSolutionList.individualSolution zip genotypes
         
      individualPerGenotypes.map{ case (individualSolution, genotype) =>
        
        val (partialSolution1, partialSolution2) = generatePartialSolution(genotype, individualSolution)
           
        val (haplotype1, haplotype2) = generateFinalPairSolution(genotype, partialSolution1, partialSolution2)
           
        addHaplotypePairToStructure(individualSolutionList.id, individualSolution, haplotype1, haplotype2, genotype.id)
         
      }
     }
    resolveSolutionsStructure
  }
  
  private def addHaplotypePairToStructure(id: Int, individualSolution: Int, haplotype1: Haplotype, haplotype2: Haplotype, refersToGenotype: Long) = {
    
    resolveSolutionsStructure = resolveSolutionsStructure :+  new ResolveSolution(id, individualSolution, haplotype1, refersToGenotype) :+  new ResolveSolution(id, individualSolution, haplotype2, refersToGenotype) 
  }
  
  private def generateIndividualSolutions(genotypes: Vector[Genotype], amountOfSolutions: Int): Vector[IndividualSolution] = {
    
    integerBoundaryBuilder.createAStructureContainingIndividualSolutions(genotypes, amountOfSolutions)
  }
  private def generatePartialSolution(genotype: Genotype, integerBoundary: Int): (String, String) = {
    
    partialSolutionGenerator.generatePartialSolution(genotype, integerBoundary)
  }
  
  private def generateFinalPairSolution(genotype: Genotype, partialSolution1: String, partialSolution2: String): (Haplotype, Haplotype) = {
    
    finalSolutionGenerator.generateFinalPairSolution(genotype, partialSolution1, partialSolution2)
  }
  
}