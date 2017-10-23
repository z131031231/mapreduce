package com.x.hadoop.mr;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 1.��������ҵ���߼�,ȷ����������ݵ���ʽ
 * 2.�Զ���һ����,�����Ҫ�̳�map����� ��дmap���� ʵ�־����ҵ���߼�,���µ�kv���
 * 2.�Զ�һ��һ����,�����Ҫ�̳�reduces ��д reduces���� ʵ�־����ҵ���߼�,���µ�kv���
 * 4.���Զ����mapper��reduces��װ����
 * @author Administrator
 *
 */
public class WordCount {

	public static void main(String[] args) throws Exception {

		
			Job job =Job.getInstance(new Configuration());
			//ע��,main�������ڵ���
			job.setJarByClass(WordCount.class);
			//����Mapper�������
			job.setMapperClass(WCMapper.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(LongWritable.class);
			FileInputFormat.setInputPaths(job, new Path("/words"));
			//����reduces����
			job.setReducerClass(WCReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(LongWritable.class);  //����ָ��MAP Ҳ����ָ��reduce ��Ϊ��ʱ�����û��reduces
			FileOutputFormat.setOutputPath(job, new Path("/wcc"));//�мǶ���д��/ 
			
			job.waitForCompletion(true);
			
	}

}
