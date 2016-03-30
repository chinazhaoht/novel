package com.china.zhaoht.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.storm.shade.org.jgrapht.util.MathUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoht
 * @date 2016/3/29 19:43
 */
public class WordCount implements IRichBolt {

    private  OutputCollector collector;

    Map<String,Integer> wordCount = new HashMap<String,Integer>();

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        //String sentence = tuple.getString(0);
        String sentence = (String)tuple.getValueByField("line");
        if(wordCount.containsKey(sentence)){
            wordCount.put(sentence,wordCount.get(sentence)+1);
        }else{
            wordCount.put(sentence,1);
        }
        System.out.println(sentence+":"+wordCount.get(sentence));
        collector.emit(new Values(sentence,wordCount.get(sentence)));

        //collector.ack(tuple);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word","count"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
