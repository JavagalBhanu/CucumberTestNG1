package com.cucumber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberFactoryBuilder {

    private List<Option> options = new ArrayList<Option>();

    private static List<String> addFeature(String basePacakge, File feature) {
    	System.out.println("Bhanu adding feature");
        String basePackagePath = basePacakge.replace(".", File.separator);
        List<String> featureTests = new ArrayList<String>();
        if (!feature.exists()) {
            throw new IllegalArgumentException("feature file does not exist");
        }

        if (feature.isDirectory()) {
            File[] files = feature.listFiles();
            for (File file : files) {
                if (!file.isHidden()) {
                    featureTests.addAll(addFeature(basePacakge, file));
                }
            }
        } else {
            if (feature.getPath().contains(basePackagePath) && feature.getName().endsWith(".feature")) {
                featureTests.add(feature.getPath());
            }
        }

        return featureTests;
    }

    private static StackTraceElement findStackTraceSource() {
        StackTraceElement[] elements = new Exception().fillInStackTrace().getStackTrace();
        for (StackTraceElement element : elements) {
            if (!CucumberFactoryBuilder.class.getName().equals(element.getClassName())) {
                return element;
            }
        }
        return null;
    }

    public CucumberFactoryBuilder addOption(String key, String value) {

        options.add(new Option(key, value));
        return this;
    }

    public Object[] create() {
        return create(new File("."));
    }

    public Object[] create(final File baseDirectory) {
    	System.out.println(baseDirectory.getAbsolutePath());
        CucumberTestImpl test;
        String sourceClass = findStackTraceSource().getClassName();
        String sourcePackage = sourceClass.substring(0, sourceClass.lastIndexOf("."));
        System.out.println(sourcePackage);
        List<Object> featureTests = new ArrayList<Object>();
        List<String> features = addFeature(sourcePackage, baseDirectory);
        System.out.println("Bhanu number of features:"+features.size());
        for (String feature : features) {
        	System.out.println("Bhanu inside loop:"+feature);
            test = new CucumberTestImpl(sourcePackage, feature);
            for (Option opt : options) {
                test.addOption(opt.key, opt.value);
            }
            featureTests.add(test);
        }
        System.out.println("Bhanu number of test:"+featureTests.size());
        return featureTests.toArray();
    }

    private class Option {
        public String key;
        public String value;

        public Option(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}
