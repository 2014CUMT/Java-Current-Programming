package phase3.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * @classname: VolatileFoo
 * @description:
 * @author: Desire
 * @date: 2019-08-07 10:54
 */
public class VolatileFoo {

    final static int MAX = 5;
    static volatile int init_value = 0;

    //时不我待
    public static void main(String[] args) {

        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {
                    System.out.printf("the initIvalue is update to [%d] \n", init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();


        new Thread(() ->{
            int localValue = init_value;
            while (localValue < MAX){
                System.out.printf("The init_value will be changed to [%d] \n",++localValue);
                init_value = localValue;
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"Updater").start();




    }


}
