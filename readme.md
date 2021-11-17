Author: Carlo van Buiten Date: 17/11/2021

## Java classifier for histological response in Eosinophilic Esophagitis (EoE) patients.

### Introduction
This program uses a cost-sensitive logistic weka model to predict whether a patient has a benign or malignant form of
breast cancer.

### Prerequisites
- Java version 14 or newer
- Weka version 3.8 
- Apache CLI version 1.4

### Installation
Download the project through one of the following 2 methods and assemble a JAR file.

##### Cloning through terminal
To clone the project through your terminal, type the following in your terminal:  
gh repo clone C-Buiten/Thema9_Wrapper

##### Downloading through git
To download the project from the online git repository, navigate to the following link:  
https://github.com/C-Buiten/Thema9_Wrapper.git

### Usage
Once a jar has been assembled, run this jar on the command line as follows:  
java -jar <(path/to/)javawrapper-1.0-SNAPSHOT.jar> -f <(path/to/)data.arff>  

A test file which is also a good demonstration of what the input data is expected to look like can be found at src/main/resources/classify_data.arff.

The program is fully functional with both .arff and .csv files, but the latter does occasionally present the user with an error,
this error can be ignored and the correct results will still be printed. Nevertheless, the usage of arff files is recommended.
Examples of what the input files should look like can be found below.

##### ARFF
@relation 'new_data'  
@attribute 'Clump_Thickness' numeric  
@attribute 'Cell_Size_Uniformity' numeric  
@attribute 'Cell_Shape_Uniformity' numeric  
@attribute 'Marginal_Adhesion' numeric  
@attribute 'Single_Epithelial_Cell_Size' numeric  
@attribute 'Bare_Nuclei' numeric  
@attribute 'Bland_Chromatin' numeric  
@attribute 'Class' {"Benign", "Malignant"}  
@data  
10,10,10,10,10,10,10,?  
1,1,1,1,1,1,1,?  
6,8,8,1,3,4,3,?  
4,1,1,3,2,1,3,?  

Note that the resulting class-names can be modified by adjusting the strings in the "Class" attribute.

##### CSV
"Clump_Thickness","Cell_Size_Uniformity","Cell_Shape_Uniformity","Marginal_Adhesion","Single_Epithelial_Cell_Size","Bare_Nuclei","Bland_Chromatin","Class"  
10,10,10,10,10,10,10,"B"  
1,1,1,1,1,1,1,"B"  
6,8,8,1,3,4,3,"M"  
4,1,1,3,2,1,3,"M"  

Note that the contents of final "Class" column are irrelevant beyond the point of providing the 2 class label names and are of no effect to the classification results.


### Troubleshooting
For more information you can email the author at:
c.van.buiten@st.hanze.nl or carlobuiten@hotmail.com