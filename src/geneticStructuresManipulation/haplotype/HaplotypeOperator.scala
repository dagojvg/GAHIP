package geneticStructuresManipulation.haplotype

import geneticStructures.Allele
import geneticStructures.Haplotype
import geneticStructures.Genotype

object HaplotypeOperator {
  
  implicit def +(_this: Haplotype, that: Haplotype): Genotype = {
    
    val haplotypesContainer = _this.haplotype zip that.haplotype
    
    val genotypeContainer = for((allele1, allele2) <- haplotypesContainer)
    		yield if(allele1 == allele2) allele1  else Allele.HETEROZYGOUS_SITE 
          
    new Genotype(genotypeContainer.mkString)
  }
}