package geneticStructuresManipulation.genotype

import geneticStructures.Allele
import geneticStructures.Genotype

object HeterozygousSite extends VaryingSite{
  
  override def amount(genotype: Genotype): Int = {
    
    lazy val filterCondition = (allele: Char) => allele == Allele.HETEROZYGOUS_SITE
    
    genotype.toString.filter(filterCondition).length
  }
}