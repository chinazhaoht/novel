package com.cyyz.novel.service.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/19.
 */
public class SeparateBolt extends BaseRichBolt {

    OutputCollector collector;
    Map<String,Integer> wordCountMap = new HashMap<String, Integer>();

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    public void execute(Tuple tuple) {
        tuple.
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
