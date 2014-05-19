package unit

import org.junit.Assert._
import org.junit.Test
import org.junit.runner.RunWith
import com.googlecode.zohhak.api.runners.ZohhakRunner
import com.googlecode.zohhak.api.TestWith
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticStructures.Genotype
import geneticStructuresManipulation.genotype.GenotypeUniqueness

@RunWith(classOf[ZohhakRunner])
class GenotypeTests {

  @TestWith(Array(
      "10100101, 0",
      "10212202, 4",
      "22022201, 5"))
  def countHeterozygousSites_collectingHeterozygousSites_returnsAmount(genotypeString: String, expectedCount: Int) = {

    val genotype = makeGenotype(genotypeString)
    
    val result = genotype amountOfHeterozygousSites
    
    assertEquals(expectedCount, result)
  }
  
  @TestWith(Array(
      "10100101, 8",
      "10212202, 4",
      "22022201, 3"))
  def countHomozygousSites_collectingHomozygousSites_returnsAmount(genotypeString: String, expectedCount: Int) = {
    
    val genotype = makeGenotype(genotypeString)
    
    val result = genotype amountOfHomozygousSites
    
    assertEquals(expectedCount, result)
  } 
  
  @Test
  def equals_comparingTwoSameGenotypes_shouldReturnTrue() = {
    
    val genotype1 = makeGenotype("10212202")
    
    val genotype2 = makeGenotype("10212202")
    
    val result = genotype1 equals genotype2 
    
    assertTrue(result)
  }
  
  @Test
  def id_afterAGenotypeIsCreated_returnsAUniqueIdentifierOfTheGenotype() = {
    
    val context = makeContext
    
    val stubGenotypeId = context.mock(classOf[GenotypeUniqueness])
    
    context.checking(
        new Expectations(){
          allowing (stubGenotypeId).generateId()
          will(returnValue(1))
        })
    val genotype = new Genotype("10212202", stubGenotypeId)
    
    val result = genotype.id
  
    assertEquals(1, result)
  }
  private def makeGenotype(genotypeString: String): Genotype = {
    new Genotype(genotypeString)    
  }
  private def makeContext: Mockery = {
    new Mockery
  }
}