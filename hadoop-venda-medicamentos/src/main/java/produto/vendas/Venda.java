package produto.vendas;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Alexandre Roggen on 29/10/16.
 */
public class Venda implements Writable {
        private double price;
        private String data;

    public Venda() {
            set(price, data);
        }
    public Venda(double price, String data) {
            set(price, data);
        }
    public void set(double first, String second) {
        this.price = first;
        this.data = second;
    }
    public double getPrice() {
        return price;
    }
    public String getData() {
        return data;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(price);
        out.writeUTF(data);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        price = in.readDouble();
        data = in.readUTF();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        if (Double.compare(venda.price, price) != 0) return false;
        return data.equals(venda.data);

    }

    @Override
    public String toString() {
        return "" +
                "preço=" + price +
                ", data_venda='" + data;
    }
}