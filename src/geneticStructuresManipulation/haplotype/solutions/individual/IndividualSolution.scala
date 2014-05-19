package geneticStructuresManipulation.haplotype.solutions.individual

class IndividualSolution(val individualSolution: Vector[Int]) extends Equals {
  
  private var ID = IndividualSolutionId.generateId()
  private var fitnessValue = -1.0
  private var ld = -1.0
  
  def id = ID
  
  def fitness = fitnessValue
  
  def fitness_= (newFitnessValue: Double){
    
    fitnessValue = newFitnessValue
    
    fitnessValue
  }
  
  def linkageDisequilibrium = ld
  
  def linkageDisequilibrium_= (newLD: Double){
    
    ld = newLD
    
    ld
  }
  def this(individualSolution: Vector[Int], individualSolutionUniqueness: IndividualSolutionUniqueness){
    this(individualSolution)
    
    ID = individualSolutionUniqueness.generateId()
  }
  
  override def canEqual(other: Any): Boolean = {
    other.isInstanceOf[IndividualSolution]
  }
  
  override def hashCode: Int = individualSolution.##
  
  override def equals(other: Any): Boolean = other match{
    case that: IndividualSolution =>
      if(this eq that){ true }
      else{
        (that.## == this.##) &&
        (that canEqual this) &&
        (that.individualSolution == this.individualSolution)
      }
  }
}