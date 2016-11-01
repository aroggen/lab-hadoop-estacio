package produto.vendas;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chamador {

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        Job job = Job.getInstance(conf, "Job Hadoop");
        job.setJarByClass(Chamador.class);
        job.setMapperClass(VendaMapper.class);
        job.setCombinerClass(VendaReducer.class);
        job.setReducerClass(VendaReducer.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Venda.class);
//        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path((otherArgs.length >= 1) ? otherArgs[0] : "entrada/"));
        FileOutputFormat.setOutputPath(job, new Path((otherArgs.length >= 2) ? otherArgs[1] : "saida/" + new SimpleDateFormat("yyMMddHHmmss").format(new Date())));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}