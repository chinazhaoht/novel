package com.china.zhaoht.spouts;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import javax.print.DocFlavor;
import java.io.*;
import java.util.Map;

/**
 * @author zhaoht
 * @date 2016/3/29 16:00
 */
public class ReadFile extends BaseRichSpout {

    private BufferedReader bufferedReader;

    private SpoutOutputCollector collector;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        System.out.println("open()......");
        File file = new File("E:\\mycode\\novel\\storm\\src\\main\\resources\\data");

        collector = spoutOutputCollector;
        try {
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nextTuple() {

        String line ;
        if(bufferedReader != null){
            try {
                while((line = bufferedReader.readLine())!=null){
                    collector.emit(new Values(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\mycode\\novel\\storm\\src\\main\\resources\\data");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line ;
        while((line = bufferedReader.readLine())!=null){
            System.out.println("################"+line);
        }
    }
}
