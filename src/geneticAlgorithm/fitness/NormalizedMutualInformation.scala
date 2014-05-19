package geneticAlgorithm.fitness

import geneticStructuresManipulation.haplotype.solutions.individual.IndividualSolution
import geneticAlgorithm.data.Data
import geneticStructures.Allele
import graph.KruskalMST
import graph.UpperTriangleUndirectedGraph
import geneticStructuresManipulation.haplotype.solutions.resolve.ResolveSolution

class NormalizedMutualInformation extends Fitness {

  private var maximumLikelihood = new MaximumLikelihood
  private var kruskalMST = new KruskalMST
 
  def calculate(data: Data, candidateSolution: IndividualSolution): Double = {
    
    if(candidateSolution.fitness == -1){
      //candidateSolution.fitness = calculateHelper(data, candidateSolution)
      candidateSolution.linkageDisequilibrium = calculateHelper(data, candidateSolution)
      
      candidateSolution.fitness = calculateLinkageDisequilibriumAndMaximumLikelihoodEstimates(data, candidateSolution)
    }    

    candidateSolution.fitness
  }

  private def calculateHelper(data: Data, candidateSolution: IndividualSolution): Double = {
    
    if(candidateSolution.linkageDisequilibrium == - 1.0){
         
       val resolveSolution = data.resolveSolutions.filter(resolution => resolution.solutionID == candidateSolution.id)
    
       val resolveSolutionLen = resolveSolution.length
       val SNPLen = resolveSolution(0).haplotype.haplotype.length
    
       val frequenciesOfSNPs = calculateFrequencies(SNPLen, resolveSolutionLen, resolveSolution, data)
             
       val jointFrequenciesOfSNps = calculateJointFrequencies(resolveSolutionLen, SNPLen, resolveSolution, data)
    
       val entropies = calculateEntropies(frequenciesOfSNPs)
    
       val jointEntropies = calculateJointEntropies(jointFrequenciesOfSNps)
    
       val mutualInformation = calculateMutualInformation(SNPLen, entropies, jointEntropies)
    
       val normalizedMutualInformation = calculateNormalizedMutualInformation(SNPLen, entropies, mutualInformation)
    
       val mstMinSNPs = calculateMSTMinSNPs(SNPLen, normalizedMutualInformation)
    
       mstMinSNPs
    } else {
      
      candidateSolution.linkageDisequilibrium
    }
    //mstMinSNPs * _maximumLikelihood
  }
  
  private def calculateLinkageDisequilibriumAndMaximumLikelihoodEstimates(data: Data, candidateSolution: IndividualSolution) = {
    
    var _maximumLikelihood = maximumLikelihood.calculate(data, candidateSolution)
    
    calculateLinkageDisequilibriumAndMaximumLikelihoodEstimatesHelper(candidateSolution.linkageDisequilibrium, _maximumLikelihood)
  }
  private def calculateLinkageDisequilibriumAndMaximumLikelihoodEstimatesHelper(linkageDisequilibrium: Double, maximumLikelihoodEstimates: Double): Double = {
    linkageDisequilibrium * maximumLikelihoodEstimates
  }
  private def log2(value: Double): Double = {
    if(value == 0.0) 0.0 else (Math.log(value) / Math.log(2.0))
  }
  
  private def calculateFrequencies(SNPLen: Int, resolveSolutionLen: Int,
                                   resolveSolution: Vector[ResolveSolution],
                                   data: Data): IndexedSeq[(Double, Double)] = {
    
    for(i<- 0 until SNPLen) yield {
      var freqOfWildType = 0.0
 
      for(j<-0 until resolveSolutionLen){
        
      val allele = resolveSolution(j).haplotype.haplotype(i)
     // val hapFreq = data.haplotypesFrequencies(resolveSolution(j).haplotype)      
      
      freqOfWildType += (if(allele == Allele.HOMOZYGOUS_WILD_TYPE_SITE) 1 else 0.0)
     
      }
      
      freqOfWildType = freqOfWildType / resolveSolutionLen.toDouble
      
      (freqOfWildType, 1.0 - freqOfWildType)
    }
  }
  
  private def calculateJointFrequencies(resolveSolutionLen: Int, SNPLen: Int, resolveSolution: Vector[ResolveSolution],
                                        data: Data): IndexedSeq[IndexedSeq[(Double, Double, Double, Double)]] = {
    
    var jF00 = 0.0
    var jF01 = 0.0
    var jF10 = 0.0
    var jF11 = 0.0
    
    for(i<-0 until SNPLen - 1) yield {      
      
      for(j<-i+1 until SNPLen) yield {     
        jF00 = 0.0
        jF01 = 0.0
        jF10 = 0.0
        jF11 = 0.0
        
        for(k<-resolveSolution){
          
          //val hapFreq = data.haplotypesFrequencies(k.haplotype)
          
          if(k.haplotype.haplotype(i) == Allele.HOMOZYGOUS_WILD_TYPE_SITE){
            
            if(k.haplotype.haplotype(j) == Allele.HOMOZYGOUS_WILD_TYPE_SITE){
            	jF00 += 1.0
            } else {
            	jF01 += 1.0
            }         
          } else {
            
            if(k.haplotype.haplotype(j) == Allele.HOMOZYGOUS_WILD_TYPE_SITE){
            	jF10 += 1.0            
            } else {
            	jF11 += 1.0
            }
          }
        }
        
      (jF00 /resolveSolutionLen.toDouble, jF01 / resolveSolutionLen.toDouble, jF10 / resolveSolutionLen.toDouble, jF11 / resolveSolutionLen.toDouble)
     }      
    }
  }
  
  private def calculateEntropies(frequenciesOfSNPs: IndexedSeq[(Double, Double)]): IndexedSeq[Double] = {
    
    frequenciesOfSNPs.map{ frequency => 
      
      val (fWildType, fMutantType) = frequency
      
      -(fWildType * log2(fWildType) + fMutantType * log2(fMutantType))
    
    }
  }
  
  private def calculateJointEntropies(jointFrequenciesOfSNps: IndexedSeq[IndexedSeq[(Double, Double, Double, Double)]]):
	  								 IndexedSeq[IndexedSeq[(Double)]]= {
    
    jointFrequenciesOfSNps.map{ jointFrequency =>
      jointFrequency.map{ jf =>
        
        val (jF00, jF01, jF10, jF11) = jf
        
        -(jF00 * log2(jF00) + jF01 * log2(jF01) + jF10 * log2(jF10) + jF11 * log2(jF11))
      }
    }
  }
  
  private def calculateMutualInformation(SNPLen: Int, entropies: IndexedSeq[Double],
		  								 jointEntropies:IndexedSeq[IndexedSeq[(Double)]]): IndexedSeq[IndexedSeq[(Double)]] ={
    
    for(i<-0 until SNPLen - 1) yield {
      
      var k = 0
      
      for(j<-i+1 until SNPLen) yield {
        
        val mi = entropies(i) + entropies(j) - jointEntropies(i)(k)
        
        k += 1
        
        mi
      }
    }
  }
  
  private def calculateNormalizedMutualInformation(SNPLen: Int, entropies: IndexedSeq[Double],
                                                   mutualInformation:IndexedSeq[IndexedSeq[(Double)]]):
                                                   IndexedSeq[IndexedSeq[(Double)]] = {
    
    for(i<-0 until SNPLen - 1) yield {
      
      var k = 0
      
      for(j<-i+1 until SNPLen) yield {
        
        val nmi = mutualInformation(i)(k) / Math.min(entropies(i), entropies(j))
        
        k += 1
        
        nmi
      }
    }
  }
  
  private def calculateMSTMinSNPs(SNPLen: Int, normalizedMutualInformation: IndexedSeq[IndexedSeq[(Double)]]): Double = {
    
    val graph = new UpperTriangleUndirectedGraph(normalizedMutualInformation)

    val (mst, predecessors) = kruskalMST.calculate(graph)
   
    (1.0 + ((predecessors.length.toDouble) / SNPLen.toDouble))
  }
}