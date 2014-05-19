package geneticStructuresManipulation.haplotype.solutions.resolve

import geneticStructures.Haplotype
import geneticStructures.Genotype
import geneticStructures.Allele

private[haplotype] class FinalSolutionGenerator {
  
  def generateFinalPairSolution(genotype: Genotype, partialSolution1: String, partialSolution2: String): (Haplotype, Haplotype) = {
    
    val haplotype1 = new Haplotype(substituteHeterozygousSites(genotype, partialSolution1))
    
    val haplotype2 = new Haplotype(substituteHeterozygousSites(genotype, partialSolution2))
    
    (haplotype1, haplotype2)
  }
  
  private def substituteHeterozygousSites(genotype: Genotype, partialSolution: String): String = {
      
    val genIterator = genotype.genotype.iterator
    val psIterator = partialSolution.iterator
    val resolveSolution = new StringBuilder
    
    while(genIterator.hasNext){
      
      val genSite = genIterator.next
      
      resolveSolution.append(if(genSite == Allele.HETEROZYGOUS_SITE){
    	  						psIterator.next
      					    } else {
      					      genSite
      					    }
      )
    }
    
    resolveSolution.toString
    
    /*var currentSite = -1
   
    genotype.genotype.map{
      case site => if(site == Allele.HETEROZYGOUS_SITE){
    	  			currentSite += 1
    	  			partialSolution(currentSite) 
      			   }else{ 
      			     site
      			   }
      }*/
  }
}