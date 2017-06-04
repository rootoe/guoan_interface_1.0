package com.guoan.guoan_interface_15;

/**
 * Created by zhaotongbeyond@qq.com on 2015/7/22.
 */
public class ThreadTest {
    public static void main(String[] args) {
        int count = 1;
        for(int i =0, len=10; i<len; i++){
            System.out.println("循环第-> "+i+" 遍");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(this){
                        for(int j =0, len1=10; j<len1; j++){
                            System.out.println("j => "+j+"  "+(count+j));
                        }
                    }
                }
            });
            thread.start();
        }
    }


}
