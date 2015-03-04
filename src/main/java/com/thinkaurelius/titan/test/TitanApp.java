package com.thinkaurelius.titan.test;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

import com.thinkaurelius.titan.hadoop.formats.cassandra.TitanCassandraInputFormat;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.io.NullWritable;
import com.thinkaurelius.titan.hadoop.FaunusVertex;
import org.apache.hadoop.mapred.JobConf;

public class TitanApp {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Titan Vertex Count");
        JavaSparkContext sc = new JavaSparkContext(conf);
    
        org.apache.hadoop.conf.Configuration jobConf = new org.apache.hadoop.conf.Configuration();
        jobConf.addResource("com/thinkaurelius/titan/test/titaninput.xml");
    
        Class<? extends InputFormat<NullWritable, FaunusVertex>> inputFormatClass = TitanCassandraInputFormat.class;
        Class<NullWritable> keyClass = NullWritable.class;
        Class<FaunusVertex> valueClass = FaunusVertex.class;
        JavaPairRDD titanRDD = sc.newAPIHadoopRDD(jobConf, inputFormatClass, keyClass, valueClass);
    
        long count = titanRDD.count();
    
        System.out.println("Vertices: " + count);
    }
}
