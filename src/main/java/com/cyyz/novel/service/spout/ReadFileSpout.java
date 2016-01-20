package com.cyyz.novel.service.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;

import java.io.*;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ReadFileSpout extends BaseRichSpout {

    SpoutOutputCollector collector;
    BufferedReader reader;

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

        this.collector= collector;
        try {
            reader = new BufferedReader(new FileReader("doi.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void nextTuple() {
        String line = "";
        while(reader != null){
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
