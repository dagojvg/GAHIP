package geneticStructuresManipulation.haplotype.solutions.resolve

import geneticStructures.Haplotype

class ResolveSolution(val solutionID: Int, val solution: Int, val haplotype: Haplotype, val refersToGenotype: Long) extends Equals{
  
  override def canEqual(other: Any): Boolean = {
    other.isInstanceOf[ResolveSolution]
  }
  
  override def hashCode: Int = solution.## + haplotype.##
  
  override def equals(other: Any): Boolean = other match{
    case that: ResolveSolution =>
      if(this eq that){ true }
      else{
        (that.## == this.##) &&
        (that canEqual this) &&
        (that.solution == this.solution && 
         that.haplotype == this.haplotype)
      }
  }
}