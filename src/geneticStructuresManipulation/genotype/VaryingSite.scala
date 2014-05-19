package geneticStructuresManipulation.genotype

import geneticStructures.Genotype

trait VaryingSite {
  
  def amount(genotype: Genotype): Int
}