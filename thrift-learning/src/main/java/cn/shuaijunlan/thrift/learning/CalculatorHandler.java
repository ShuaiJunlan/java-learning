package cn.shuaijunlan.thrift.learning;

import shared.SharedStruct;
import tutorial.Calculator;
import tutorial.InvalidOperation;
import tutorial.Work;

import java.util.HashMap;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:52 AM 11/5/18.
 */
public class CalculatorHandler implements Calculator.Iface {
    private HashMap<Integer,SharedStruct> log;

    public CalculatorHandler() {
        log = new HashMap<>();
    }

    @Override
    public void ping() {
        System.out.println("ping()");
    }

    @Override
    public int add(int n1, int n2) {
        System.out.println("add(" + n1 + "," + n2 + ")");
        return n1 + n2;
    }

    @Override
    public int calculate(int logid, Work work) throws InvalidOperation {
        System.out.println("calculate(" + logid + ", {" + work.op + "," + work.num1 + "," + work.num2 + "})");
        int val = 0;
        switch (work.op) {
            case ADD:
                val = work.num1 + work.num2;
                break;
            case SUBTRACT:
                val = work.num1 - work.num2;
                break;
            case MULTIPLY:
                val = work.num1 * work.num2;
                break;
            case DIVIDE:
                if (work.num2 == 0) {
                    InvalidOperation io = new InvalidOperation();
                    io.whatOp = work.op.getValue();
                    io.why = "Cannot divide by 0";
                    throw io;
                }
                val = work.num1 / work.num2;
                break;
            default:
                InvalidOperation io = new InvalidOperation();
                io.whatOp = work.op.getValue();
                io.why = "Unknown operation";
                throw io;
        }

        SharedStruct entry = new SharedStruct();
        entry.key = logid;
        entry.value = Integer.toString(val);
        log.put(logid, entry);

        return val;
    }

    @Override
    public SharedStruct getStruct(int key) {
        System.out.println("getStruct(" + key + ")");
        return log.get(key);
    }

    @Override
    public void zip() {
        System.out.println("zip()");
    }
}
