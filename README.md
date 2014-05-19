A Genetic Algorithm Using Maximum Likelihood Estimates & Normalized Mutual Information
======================================================================================
**Background**
------------------------

Haplotypes consist of blocks of single nucleotide polymorphisms (SNPs). 
Haplotypes being a  unit  of  inheritance  are  widely  used  for  association  studies  and  gene  candidate  studies. 
However, obtaining these blocks of SNPs through in vitro  methods is both time consuming and  expensive.
In  silico  studies  try  to  infer  haplotypes  from  genotypic  data.
This program utilizes a genetic algorithm (i.e. a heuristic approach) guided through two genetic models, 
essentially the Hardy-Weinberg equilibrium and linkage disequilibrium. These have been
statistically assessed by maximum likelihood estimates and a normalized mutual information 
respectively.  This  algorithm  generates  an  adequate  solution  in  polynomial  time  to  an 
inherently NP-Hard problem.  The results showed that  this algorithm  has a better  accuracy 
rate compared to a genetic algorithm that only utilizes the Hardy-Weinberg equilibrium.

**Genetic Algorithm Settings**
------------------------------

| **Operator**  | **Description**                 |
|---------------|---------------------------------|
| `Selection`   | `Tournament Strategy Size Of 5` |
| `Crossover`   | `Uniform Crossover Rate of 0.6` |
| `Mutation`    | `Uniform Mutation Rate of 0.1`  |

`An elitist approach is used in order to keep the selected parents onto the next generation.`

*Fitness Function*

![Alt text](/FitnessFunction.PNG?raw=true "Fitness Function")

The  above  fitness  function  incorporates  the  percentage  of  single nucleotide polymorphisms  that  are  in  low  linkage disequilibrium given a set of haplotypes.
It also includes the maximum likelihood of haplotype frequencies given a vector of genotypes. 
This means that the maximum likelihood of haplotype frequencies depend on the amount of haplotypes found in the entire population of a particular generation.
Whereas the measure of the linkage disequilibrium accounts for the haplotypes found in one individual from the population of a particular generation;
so as to quantify the amount of single nucleotide polymorphisms that are in low linkage disequilibrium.

**Implementation Description**
------------------------------


| **Tools used** |
|----------------|
| Scala 2.10.3   |
|  JUnit 4       |
| jMock 2.6.0    |
| Zohhak 1.0.2   |

**Running the app**
-------------------

To run this app, execute the following command:
	`scala GAHIP Genotype_File_Path Result_File_Path`

Where `Genotype_File_Path` is the path of the files containing the genotypic data. 
`Result_File_Path` is the path where the app will write the haplotypes that define the genotypes.

Example:
	`scala GAHIP Beta2_AR_Genotypes.txt Beta2_AR_Haplotypes.txt`

The `Beta2_AR_Genotypes.txt` file can be found under the integration tests folder.
The Beta 2 –Adrenergic receptors dataset  has 18 genotypes and 12 SNP sites.
This 18 genotypes are defined by 10 haplotypes.