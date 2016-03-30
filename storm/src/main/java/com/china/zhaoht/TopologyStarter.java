package com.china.zhaoht;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.china.zhaoht.bolts.WordCount;
import com.china.zhaoht.bolts.WordFrequencyBolt;
import com.china.zhaoht.spouts.ReadFile;

import java.io.FileReader;

/**
 * @author zhaoht
 * @date 2016/3/28 15:53
 */
public class TopologyStarter {
    public static void main(String[] args) throws InterruptedException {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader",new ReadFile(),3);
        builder.setBolt("word-count",new WordCount(),4).shuffleGrouping("word-reader");
        builder.setBolt("word-frequency",new WordFrequencyBolt(),5).fieldsGrouping("word-count",new Fields("word","count"));

        Config conf = new Config();
        //conf.put("wordsFile",args[0]);
        conf.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("started-Topologie",conf,builder.createTopology());
        Thread.sleep(2000);
       // cluster.shutdown();
    }
}
