package mydev.perceptron; public interface Perceptronic { void initNodesClearState(); void pushDataLearnItem(ClassificationProbe sample,ClassificationResult expected); int countItemsLearned(); void processDataLearnItems(); ClassificationResult classifyDataResult(ClassificationProbe sample); int countItemsClassify(); void clear();}