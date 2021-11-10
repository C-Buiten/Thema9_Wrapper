import weka.classifiers.rules.OneR;
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
        OneR oneR = loadModel();
        classify(oneR, data);
    }

    private OneR loadModel() throws Exception {
        String model = "src/main/resources/oneR.model";
        return (OneR) weka.core.SerializationHelper.read(model);
    }

    private void classify(OneR concept, Instances data) throws Exception {
        Instances instances = new Instances(data);
        for (int i = 0; i < data.numInstances(); i++){
            double label = concept.classifyInstance(data.instance(i));
            instances.instance(i).setClassValue(label);
        }
        System.out.println(instances);
    }
}