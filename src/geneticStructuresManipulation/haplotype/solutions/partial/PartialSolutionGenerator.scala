package geneticStructuresManipulation.haplotype.solutions.partial

import geneticStructures.Genotype
import geneticStructures.Allele

private[haplotype] class PartialSolutionGenerator {
  
  private val PARTIAL_SOLUTION_PAD = "0"
    
  def generatePartialSolution(genotype: Genotype, integerBoundary: Int): (String, String) = {
    
	val partialSolution1 = generatePartialSolution1(genotype, integerBoundary)
	
    val partialSolution2 = generatePartialSolution2(partialSolution1)
    
    (partialSolution1, partialSolution2)
  }
  
  private def generatePartialSolution1(genotype: Genotype, integerBoundary: Int): String = {
    
    var binaryRepresentation = integerBoundary.toBinaryString
         
    val partialSolution1 = PARTIAL_SOLUTION_PAD * 
                            (genotype.amountOfHeterozygousSites - binaryRepresentation.length) + 
                            binaryRepresentation
     
     partialSolution1
  }
  
  private def generatePartialSolution2(partialSolution1: String): String = {
    
    val ps1Iterator = partialSolution1.iterator
    
    val partialSolution2 = new StringBuilder
    
    while(ps1Iterator.hasNext){
      
      partialSolution2.append(
          if(ps1Iterator.next == Allele.HOMOZYGOUS_WILD_TYPE_SITE){
        	  Allele.HOMOZYGOUS_MUTANT_SITE
          }
          else{
        	  Allele.HOMOZYGOUS_WILD_TYPE_SITE
          }
      )
    }
    partialSolution2.toString
    /*
    val partialSolution22 = partialSolution1.map{
      case site => if(site ==  Allele.HOMOZYGOUS_WILD_TYPE_SITE){
    	  		     Allele.HOMOZYGOUS_MUTANT_SITE
      			   } else{
      			     Allele.HOMOZYGOUS_WILD_TYPE_SITE
      			   }
     }
    
    partialSolution22*/
    
    
  }
}