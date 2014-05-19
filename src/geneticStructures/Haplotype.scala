package geneticStructures

import geneticStructuresManipulation.haplotype.HaplotypeOperator
import geneticStructuresManipulation.haplotype.HaplotypeUniqueness
import geneticStructuresManipulation.haplotype.HaplotypeId
import geneticStructuresManipulation.haplotype.HaplotypeOperator

class Haplotype (val haplotype: String) extends Equals{
 
  private var ID = HaplotypeId.generateId()
 
  def id = ID
  
  def this(haplotype: String, haplotypeId: HaplotypeUniqueness){
    this(haplotype)
    
    ID = haplotypeId.generateId()
  }
  
  def +(that: Haplotype) = HaplotypeOperator.+(this, that)
  
  override def toString = haplotype
  
  override def canEqual(other: Any): Boolean = {
    other.isInstanceOf[Haplotype]
  }
  
  override def hashCode: Int = haplotype.##
  
  override def equals(other: Any): Boolean = other match{
    case that: Haplotype =>
      if(this eq that){ true }
      else{
        (that.## == this.##) &&
        (that canEqual this) &&
        (that.haplotype == this.haplotype)
      }
  }
  
}