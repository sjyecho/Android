package com.example.reference;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * @ClassName TestWeakReference
 * @Description TODO 弱引用
 * @Auther Shui Junyang
 * @Date 2022/5/26 下午1:26
 * @Version 1.0
 */
public class TestWeakReference {

    public static void main(String[] args) throws Exception {

        BufferedWriter bw = new BufferedWriter(new FileWriter("reference/loop.txt", true));//第二个参数设置为true，表示追加写入而不是覆盖写入

        Person p = new Person("张三", 18, "男");
        WeakReference<Person> weakPerson = new WeakReference<>(p);
        //Person person = weakPerson.get();
        //System.out.println("p和person是否相等？ " + (p == person));//true
        int i = 0;
        while (true) {
            if (weakPerson.get() != null) {
                i++;
//                System.out.println(p.getName() + ":  " + i);
                System.out.println("Object is alive for " + i + " loops - " + weakPerson);
            } else {
                System.out.println("Object has been collected. 此次共循环了： " + i + "次");
                bw.write("弱引用对象被回收前，共循环了： " + i + "次");//结果写入到一个文件中
                bw.newLine();
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
