package geneticStructuresManipulation.haplotype

object HaplotypeId extends HaplotypeUniqueness{
  
  private var id: Int = 0
	  
  def generateId() = {
    id += 1
	id
  }
}