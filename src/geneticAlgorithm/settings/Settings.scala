package geneticAlgorithm.settings

class Settings(private val _amountOfSolutions: Int) {

  private var _nGenerations = 0
  private var _crossoverRate = 0.0
  private var _mutationRate = 0.0
  private var _kRounds = 0
  
  def amountOfSolutions  = _amountOfSolutions
  
  def nGenerations = _nGenerations
  
  def crossoverRate = _crossoverRate
  
  def mutationRate = _mutationRate
  
  def kRounds = _kRounds
  
  def this(amountOfSolutions: Int, nGenerations: Int, crossoverRate: Double, mutationRate: Double, kRounds: Int){
    this(amountOfSolutions)
    
    _nGenerations = nGenerations
    _crossoverRate = crossoverRate
    _mutationRate = mutationRate
    _kRounds = kRounds
  }
}