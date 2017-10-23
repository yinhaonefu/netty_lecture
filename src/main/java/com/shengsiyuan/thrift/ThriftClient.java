package com.shengsiyuan.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * Created by yinhao on 2017/6/17.
 */
public class ThriftClient {
    public static void main(String[] args) {
        //传输同服务端
        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1",8899),600);
        //协议同服务端
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByUsername("张三");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            Person person1 = new Person();
            person1.setUsername("李四");
            person1.setAge(30);
            person1.setMarried(true);

            client.savePerson(person1);

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }finally {
            transport.close();
        }
    }
}
