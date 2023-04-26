package com.imooc.bigdata.hadoop.access;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 1. 按照 Hadoop 的规范需要实现 org.apache.hadoop.io.Writable 接口
 * 2. 需要实现 Writable 接口中的两个方法 write && readFields
 * 3. 定义一个 默认的构造方法
 */

public class Access implements Writable {

    public String phone;
    public long up;
    public long down;
    public long sum;

    public Access() {}

    public Access(String phone, long up, long down) {
        this.phone = phone;
        this.up = up;
        this.down = down;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        /**
         * Java 中指向对象的变量是引用类型的变量
         */
        out.writeUTF(this.phone);
        out.writeLong(this.up);
        out.writeLong(this.down);
        out.writeLong(this.sum);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        /**
         * 要和 write 中的顺序保持一致
         * 按照读取的数据类型判断 占用的空间大小
         */
        this.phone = in.readUTF();
        this.up = in.readLong();
        this.down = in.readLong();
        this.sum = in.readLong();
    }

    public String getPhone() {
        return phone;
    }

    public long getUp() {
        return up;
    }

    public long getDown() {
        return down;
    }

    public long getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Access{" +
                "phone='" + phone + '\'' +
                ", up=" + up +
                ", down=" + down +
                ", sum=" + sum +
                '}';
    }
}
