package geneticStructures

import geneticStructuresManipulation.genotype.GenotypeUniqueness
import geneticStructuresManipulation.genotype.GenotypeId
import geneticStructuresManipulation.genotype.HeterozygousSite
import geneticStructuresManipulation.genotype.HomozygousSite
import geneticStructuresManipulation.genotype.GenotypeId
import geneticStructuresManipulation.genotype.HeterozygousSite
import geneticStructuresManipulation.genotype.HomozygousSite

class Genotype (val genotype: String) extends Equals{
  
  private var ID = GenotypeId.generateId()
  
  private lazy val totalAmountOfHeterozygousSites = HeterozygousSite.amount(this)
  private lazy val totalAmountOfHomozygousSites = HomozygousSite.amount(this)
 
  def id = ID
  
  def amountOfHeterozygousSites = totalAmountOfHeterozygousSites
  
  def amountOfHomozygousSites = totalAmountOfHomozygousSites
  
  def this(genotype:String, genotypeId: GenotypeUniqueness) {
    this(genotype)
    
    ID = genotypeId.generateId()
  }
  override def toString = genotype
  
  override def canEqual(other: Any): Boolean = {
    other.isInstanceOf[Genotype]
  }
  
  override def hashCode: Int = genotype.##
  
  override def equals(other: Any): Boolean = other match{
    
    case that: Genotype =>
      if(this eq that){ true}
      else{
        (that.## == this.##) &&
        (that canEqual this) &&
        (that.genotype == this.genotype)
      }
      case _ => false
    }
  
}