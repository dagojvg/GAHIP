package geneticStructuresManipulation.haplotype.solutions.individual

object IndividualSolutionId extends IndividualSolutionUniqueness{

  private var id: Int = 0
	  
  def generateId() = {
    id += 1
	id
  }
}