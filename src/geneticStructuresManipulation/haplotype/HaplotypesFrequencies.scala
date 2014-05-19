package geneticStructuresManipulation.haplotype

import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import scala.collection.parallel.immutable.ParMap

class HaplotypesFrequencies {
  
  private var haplotypesFrequenciesStructure = ParMap.empty[Haplotype, Double]

  def haplotypesFrequencies = haplotypesFrequenciesStructure
  
  def calculate(resolveSolutions: Vector[ResolveSolution]): ParMap[Haplotype, Double] = {

    val resolveSolutionsLength = resolveSolutions.length.toDouble

    haplotypesFrequenciesStructure = resolveSolutions.
                                     groupBy(resolveSolution => resolveSolution.haplotype).par.
                                     map(haplotype => (haplotype._1, haplotype._2.size / resolveSolutionsLength))
    
    haplotypesFrequenciesStructure
  }
}