package unit

import org.junit.Assert._
import org.junit.Test
import geneticStructures.Haplotype
import geneticStructuresManipulation.haplotype.HaplotypesFrequencies
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution
import scala.collection.parallel.immutable.ParMap

class HaplotypesFrequenciesTests {

  @Test
  def calculateHaplotypesFrequencies_afterCreatingTheStructureThatContainsHaplotypes_returnsTheFrequenciesOfHaplotypes() = {
    
    val listOfHaplotypes = Vector(
        new ResolveSolution(1, 1, new Haplotype("10010101"), 1), 
        new ResolveSolution(1, 1, new Haplotype("10111000"), 1),
        new ResolveSolution(1, 2, new Haplotype("10010111"), 2), 
        new ResolveSolution(1, 2, new Haplotype("10111010"), 2),
        new ResolveSolution(1, 3, new Haplotype("10010101"), 3),
        new ResolveSolution(1, 3, new Haplotype("11111000"), 3)
        )
        
    val haplotypesFrequencies = new HaplotypesFrequencies
    
    val expectedResult = ParMap(
        new Haplotype("10010101") -> 0.3333333333333333,
        new Haplotype("10111000") -> 0.16666666666666666,
        new Haplotype("10010111") -> 0.16666666666666666,
        new Haplotype("10111010") -> 0.16666666666666666,
        new Haplotype("11111000") -> 0.16666666666666666)
     
     val result = haplotypesFrequencies.calculate(listOfHaplotypes)
     
     assertEquals(expectedResult, result)
     
  }
}