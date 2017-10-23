package com.shengsiyuan.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

/**
 * Created by yinhao on 2017/6/17.
 */
public class ThriftServer {
    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());//协议
        arg.transportFactory(new TFramedTransport.Factory());//传输
        arg.processorFactory(new TProcessorFactory(processor));

        TServer tServer = new THsHaServer(arg);//半同步半异步服务

        System.out.println("Thrift server started");

        tServer.serve();//死循环
    }
}
