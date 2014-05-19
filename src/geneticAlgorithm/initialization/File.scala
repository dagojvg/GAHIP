package geneticAlgorithm.initialization

class File extends DataSource{

  def extractContentAsStrings(filePath: String): Either[java.io.FileNotFoundException, Vector[String]] = {
    
    try
    {
      val file = io.Source.fromFile(filePath)
    
      val Vector = file.getLines.toVector
    
      file.close()
    
      Right(Vector)
    }catch {
      case e: java.io.FileNotFoundException => Left(e)
    }
    
  }
  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    
    val p = new java.io.PrintWriter(f)
    
    try { op(p) } finally { p.close() }
  }
}