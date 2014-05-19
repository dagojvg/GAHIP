package unit

import org.junit.Assert._
import org.junit.Test
import geneticStructuresManipulation.genotype.GenotypeBuilder
import geneticStructures.Genotype

class GenotypeBuilderTests {
  
  @Test
  def createGenotypes_whenInitializationOfObjectivesOccurs_returnsStructureContainingGenotypes() = {
    
    val genotypeBuilder = makeGenotypeBuilder
    val genotypesString = Vector("10100101", "10212202", "22022201")
    val expectedResult = Vector(
        new Genotype("10100101"),
        new Genotype("10212202"),
        new Genotype("22022201"))
    
    val result = genotypeBuilder.createGenotypes(genotypesString)
        
    assertTrue(expectedResult == result)
    
  }
  
  private def makeGenotypeBuilder: GenotypeBuilder = {
    new GenotypeBuilder
  }
}