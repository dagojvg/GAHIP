package geneticStructuresManipulation.genotype

import geneticStructures.Genotype

class GenotypeBuilder {
  
  private var genotypesStructure = Vector.empty[Genotype]
  
  def genotypes = genotypesStructure
  
  def createGenotypes(genotypesString: Vector[String]): Vector[Genotype] = {
   
   genotypesStructure = genotypesString.map(genotype=> new Genotype(genotype))
   
   genotypesStructure
  }
  
}