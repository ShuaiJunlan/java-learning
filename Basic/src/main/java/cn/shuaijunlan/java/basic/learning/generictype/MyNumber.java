package cn.shuaijunlan.java.basic.learning.generictype;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:33 PM 5/23/19.
 */
public class MyNumber {
    private Number number;

    public <T extends Integer> void setNumber(T t){
        this.number = t;
    }

    public <T extends Float> void  setNumber(T t){
        this.number = t;
    }

    public Number getNumber(){
        return number;
    }

    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        myNumber.setNumber(12);
        // myNumber.setNumber(12.1D); //error
        myNumber.setNumber(133.4F);

        System.out.println(myNumber.getNumber());
    }
}
