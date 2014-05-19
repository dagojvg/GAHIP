package geneticStructuresManipulation.genotype

import geneticStructures.Allele
import geneticStructures.Genotype

object HomozygousSite extends VaryingSite {

  override def amount(genotype: Genotype): Int = {
    
    lazy val filterCondition = (allele : Char) => allele == Allele.HOMOZYGOUS_WILD_TYPE_SITE || 
    		  								      allele == Allele.HOMOZYGOUS_MUTANT_SITE
      
    genotype.toString.filter(filterCondition).length
   }    
}