package com.china.zhaoht;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * @author zhaoht
 * @date 2016/3/30 14:28
 */
public class SparkStart {


    public static void main(String[] args) {
        String fileName = "data.txt";
        JavaSparkContext sc = new JavaSparkContext("local","Simple App",
                fileName,new String[]{"target/simple-project-1.0.jar"});

        JavaRDD<String> data = sc.textFile(fileName).cache();
        long numAs = data.filter(new Function<String, Boolean>() {
            public Boolean call(String v1) throws Exception {
                return v1.contains("apple");
            }
        }).count();

        long numBs = data.filter(new Function<String, Boolean>() {
            public Boolean call(String v1) throws Exception {
                return v1.contains("google");
            }
        }).count();

        System.out.println("Lines with a:"+numAs + ",lines with b" + numBs);
    }


}
