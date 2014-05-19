package integration

import org.junit.Assert._
import org.junit.Test
import geneticAlgorithm.initialization.File
import geneticAlgorithm.initialization.Initialization
import geneticAlgorithm.settings.Settings

class InitializationTests {
  
  @Test
  def buildInitialSolutions_whenStartingTheGeneticAlgorithm_returnsInitialSolutions() = {
    
    val filePath = "C:/Temp/Beta2_AR_Genotypes.txt"
    val file = new File
    
    val amountOfSolutions = 1000
    
    val settings = new Settings(amountOfSolutions)
    
    file.extractContentAsStrings(filePath) match {
      case Right(genotypesString) => {
        
        val initialization = new Initialization(genotypesString, settings)
    
        val result = initialization.buildInitialSolutions()
    
        assertTrue(result)
      }
      case _ =>
    }
              
  }
}