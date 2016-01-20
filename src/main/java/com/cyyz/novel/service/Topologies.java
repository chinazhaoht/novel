package com.cyyz.novel.service;

import backtype.storm.topology.TopologyBuilder;
import com.cyyz.novel.service.spout.ReadFileSpout;

/**
 * Created by Administrator on 2016/1/19.
 */
public class Topologies {

    public static void main(String[] args){
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("file",new ReadFileSpout());

    }
}
