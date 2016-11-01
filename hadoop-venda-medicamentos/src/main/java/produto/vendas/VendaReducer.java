package produto.vendas;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandre Roggen on 29/10/16.
 */
public class VendaReducer extends Reducer<Text, Venda, Text, Venda> {

    @Override
    protected void reduce(Text key, Iterable<Venda> values, Context context) throws IOException, InterruptedException {
        final double[] minValue = {Double.MAX_VALUE};
        Map<Double, String> map = new HashMap<Double, String>();
        values.forEach(venda -> {
            minValue[0] = Math.min(minValue[0], venda.getPrice());
            map.put(venda.getPrice(), venda.getData());
        });
        context.write(key, new Venda(minValue[0], map.get(minValue[0])));
    }
}