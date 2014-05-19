package geneticStructuresManipulation.genotype

object GenotypeId extends GenotypeUniqueness{
  
  private var id: Int = 0
  
  def generateId() = {
    id += 1
    id
  }
}