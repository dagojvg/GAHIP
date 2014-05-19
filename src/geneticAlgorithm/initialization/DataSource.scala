package geneticAlgorithm.initialization

trait DataSource {
  
  def extractContentAsStrings(filePath: String): Either[java.io.FileNotFoundException, Vector[String]]
}