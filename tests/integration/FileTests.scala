package integration

import org.junit.Assert._
import org.junit.Test
import geneticAlgorithm.initialization.File

class FileTests {
  
  @Test
  def readFile_whenReadingGenotypes_returnsListOfGenotypesStrings() = {
    
    val filePath = "C:/Temp/Beta2_AR_Genotypes.txt"
    val file = new File
    
    val expectedResult = Vector(
        "202222222000",
		"100111101000",
		"200222202202",
		"001000010000",
		"002000020202",
		"202222202000",
		"002000020200",
		"202000010000",
		"200000020202",
		"200222202000",
		"222222222000",
		"200222202222",
		"202222222202",
		"021000010000",
		"001000020000",
		"002000020222",
		"001000010202",
		"000000000121")
    
    file.extractContentAsStrings(filePath) match {
      case Right(result) => assertEquals(expectedResult, result)
      case _ => 
    }
    
  }
  
  @Test
  def readFile_whenReadingGenotypesOfANonExistentFile_returnsIOException() = {
    
    val filePath = "NonExistentFile.txt"
    val file = new File
    
    file.extractContentAsStrings(filePath) match{      
      case Left(exception) => exception.printStackTrace()
      case _=> 
    }
  }
}