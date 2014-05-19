package unit

import org.junit.Assert._
import org.junit.Test
import org.jmock.Mockery
import org.jmock.Expectations
import org.jmock.Expectations._
import geneticAlgorithm.initialization.DataSource

class FileTests {
  
  @Test
  def readFile_whenReadingGenotypes_returnsListOfGenotypesStrings() = {
    
    val context = makeContext
    val filePath = "C:/Temp/Beta2_AR_Genotypes.txt"
      
    val mockFileSource = context.mock(classOf[DataSource])
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
		
    context.checking(
        new Expectations(){
          allowing(mockFileSource).extractContentAsStrings(`with`(any(classOf[String])))
          will(returnValue(Right(Vector(
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
		"000000000121"))))
        })
        
     mockFileSource.extractContentAsStrings(filePath) match {
      case Right(result) => assertEquals(expectedResult, result)
      case _ =>
    }
  }
  
  @Test
  def readFile_whenReadingGenotypesFromANonExistingLocation_returnsIOException() = {
    
    val context = makeContext
    val filePath = "C:/DoesNotExists.txt"
      
    val mockFileSource = context.mock(classOf[DataSource])
    val exception = new java.io.FileNotFoundException("Not found!")
    
    context.checking(
        new Expectations(){
          allowing(mockFileSource).extractContentAsStrings(`with`(any(classOf[String])))
          will(returnValue(Left(exception)))
        })
        
    mockFileSource.extractContentAsStrings(filePath) match {
      case Left(result) => assertEquals(exception, result)
      case _ =>
    }
  }
  private def makeContext: Mockery = {
    new Mockery
  }
}