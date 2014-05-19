package geneticAlgorithm.operators.mutation

import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.operators.Operator
import geneticStructuresManipulation.haplotype.solutions.individual.IntegerBoundaryBuilder
import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolutionBuilder
import geneticStructures.Genotype

class Mutation(val rate: Double){
  
  private var operator: Operator = UniformMutationOperator
  private var individualBoundaryBuilder: IntegerBoundaryBuilder =  new IndividualSolutionBuilder
  
  def this(rate: Double, operator: Operator, individualBoundaryBuilder: IntegerBoundaryBuilder){
    this(rate)
    
    this.operator = operator
    this.individualBoundaryBuilder = individualBoundaryBuilder
  }
  
  def perform(genotypes: Vector[Genotype], offspring1: IndividualSolution, offspring2: IndividualSolution): (IndividualSolution, IndividualSolution) = {
 
    (new IndividualSolution(performHelper(genotypes, offspring1)), new IndividualSolution(performHelper(genotypes, offspring2)))
  }
  
  private def performHelper(genotypes: Vector[Genotype], offspring: IndividualSolution): Vector[Int] = {
    
    val offspringPerGenotypes = offspring.individualSolution zip genotypes
    
    offspringPerGenotypes.map{ case(solution, genotype) =>
      
      if(operator.get <= rate){
        
        individualBoundaryBuilder.generateRandomIntegerBoundary(genotype)
        
      }
      else{
        
        solution
        
      }
    }
  }
 }