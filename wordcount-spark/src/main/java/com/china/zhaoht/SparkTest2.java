package com.china.zhaoht;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zhaoht
 * @date 2016/3/30 15:00
 */
public class SparkTest2 {

    public static final Pattern SPACE  = Pattern.compile(" ");
    public static void main(String[] args) {

        //对于所有Spark程序而言，要进行任何操作，首先要创建一个Spark的上下文
        //在创建上下文的过程中，程序会向集群申请资源以及构建相应的运行环境
        SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount");
        JavaSparkContext context = new JavaSparkContext(sparkConf);

        String filename = "data.txt";
        //利用textFile接口从文件系统读入指定的文件，返回一个RDD实例对象
        //RDD的初始创建都是由SparkContext来负责的，将内存的集合或者外部文件系统作为输入源
        JavaRDD<String> lines = context.textFile(filename);
        JavaRDD<String> words = lines.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public Iterable<String> call(String s) throws Exception {
                        return Arrays.asList(SPACE.split(s));
                    }
                }
        );

        JavaPairRDD<String,Integer> ones = words.map(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {

                return new Tuple2<String, Integer>(s,1);
            }
        });

        JavaPairRDD<String,Integer> counts = ones.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }
        );
        List<Tuple2<String,Integer>> output = counts.collect();
        for(Tuple2<?,?> tuple : output){
            System.out.println(tuple._1 + ": " + tuple._2());
        }
        context.stop();
    }
}
