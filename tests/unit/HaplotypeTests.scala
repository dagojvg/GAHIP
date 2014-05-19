package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructuresManipulation.haplotype.HaplotypeUniqueness
import geneticStructures.Haplotype

class HaplotypeTests {
  
  @Test
  def id_afterAGenotypeIsCreated_returnsAUniqueIdentifierOfTheGenotype() = {
    
    val context = makeContext
    
    val stubHaplotypeId = context.mock(classOf[HaplotypeUniqueness])
    
    context.checking(
        new Expectations(){
          allowing (stubHaplotypeId).generateId()
          will(returnValue(1))
        })
    val haplotype = new Haplotype("1011001", stubHaplotypeId)
    
    val result = haplotype.id
  
    assertEquals(1, result)
  }
  
  private def makeContext: Mockery = {
    new Mockery
  }
}