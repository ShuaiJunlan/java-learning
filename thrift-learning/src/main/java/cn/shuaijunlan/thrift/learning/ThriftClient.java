package cn.shuaijunlan.thrift.learning;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import shared.SharedStruct;
import tutorial.Calculator;
import tutorial.InvalidOperation;
import tutorial.Operation;
import tutorial.Work;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:13 AM 11/5/18.
 */
public class ThriftClient {
    public static void main(String[] args) {
        // if (args.length != 1){
        //     System.out.println("Please enter 'simple' or 'secure'!");
        //     System.exit(0);
        // }
        try {
            TTransport tTransport;
            if (args.length == 0 || args[0].contains("simple")){
                tTransport = new TSocket("localhost", 9090);
                tTransport.open();
            }else {
                TSSLTransportFactory.TSSLTransportParameters parameters = new TSSLTransportFactory.TSSLTransportParameters();
                parameters.setTrustStore("../../lib/java/test/.truststore", "thrift", "SunX509", "JKS");
                tTransport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, parameters);
            }
            TProtocol tProtocol = new TBinaryProtocol(tTransport);
            Calculator.Client client = new Calculator.Client(tProtocol);
            perform(client);
            tTransport.close();
        }catch (TException x){
            x.printStackTrace();
        }
    }
    private static void perform(Calculator.Client client) throws TException {
        client.ping();
        System.out.println("ping()");

        int sum = client.add(1, 1);
        System.out.println("1+1=" + sum);

        Work work = new Work();
        work.op = Operation.DIVIDE;
        work.num1 = 1;
        work.num2 = 0;
        try {
            int quotient = client.calculate(1, work);
            System.out.println("Whoa we can divide by 0");
        } catch (InvalidOperation io) {
            System.out.println("Invalid operation: " + io.why);
        }

        work.op = Operation.SUBTRACT;
        work.num1 = 15;
        work.num2 = 10;
        int diff = client.calculate(1, work);
        System.out.println("15-10=" + diff);

        SharedStruct log = client.getStruct(1);
        System.out.println("Check log: " + log.value);
    }
}
