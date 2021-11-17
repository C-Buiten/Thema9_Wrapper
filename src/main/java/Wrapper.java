import weka.classifiers.AbstractClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;

public class Wrapper {

    public static void main(String[] args) throws Exception {
        Wrapper runner = new Wrapper();

        OptionSetter optionSetter = new OptionSetter(args);
        String file = optionSetter.parseOptions(OptionSetter.defineOptions());
        System.out.println(file);

        runner.run(file);
    }

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

    private AbstractClassifier loadModel() throws Exception {
        String model = "src/main/resources/breastcancer.model";
        return (AbstractClassifier) weka.core.SerializationHelper.read(model);
    }

    private void classify(AbstractClassifier classifier, Instances data) throws Exception {
        Instances instances = new Instances(data);
        for (int i = 0; i < data.numInstances(); i++){
            double label = classifier.classifyInstance(data.instance(i));
            instances.instance(i).setClassValue(label);
        }
        System.out.println(instances);
    }
}