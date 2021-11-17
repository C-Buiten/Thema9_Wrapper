import weka.classifiers.AbstractClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;

// This class uses a cost sensitive logistic classifier to classify data from an input file in .arff or .csv format.
public class Wrapper {
    // Self-initialize and call on the commandline parser.
    public static void main(String[] args) throws Exception {
        Wrapper runner = new Wrapper();

        OptionSetter optionSetter = new OptionSetter(args);
        String file = optionSetter.parseOptions(OptionSetter.defineOptions());
        System.out.println(file);

        runner.run(file);
    }

    // Load the data file at the target path and call on the function that loads the model.
    private void run(String file) throws Exception {
        Instances data;
        try {
            DataSource source = new DataSource(file);
             data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }
        } catch (Exception e) {
            throw new IOException("Error reading file.");
        }
        AbstractClassifier abstractClassifier = loadModel();
        classify(abstractClassifier, data);
    }

    // Load the model
    private AbstractClassifier loadModel() throws Exception {
        String model = "src/main/resources/breastcancer.model";
        return (AbstractClassifier) weka.core.SerializationHelper.read(model);
    }

    /** Function that calls on the model to the classifying of the input file and subsequently print the classified
     * results. **/
    private void classify(AbstractClassifier classifier, Instances data) throws Exception {
        Instances instances = new Instances(data);
        for (int i = 0; i < data.numInstances(); i++){
            double label = classifier.classifyInstance(data.instance(i));
            instances.instance(i).setClassValue(label);
        }
        System.out.println(instances);
    }
}