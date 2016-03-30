package com.china.zhaoht.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * @author zhaoht
 * @date 2016/3/30 11:01
 */
public class WordFrequencyBolt implements IRichBolt {

    private OutputCollector collector;

    private Integer wordTotal;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String word = (String)tuple.getValueByField("word");
        Integer count = (Integer)tuple.getValueByField("count");
        wordTotal = wordTotal + count;
        System.out.println(word + ":" + count / wordTotal);

    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
