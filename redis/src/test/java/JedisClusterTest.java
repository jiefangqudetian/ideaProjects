import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisClusterTest {

    private JedisCluster jedisCluster;

    @Before
    public void before(){
        //连接池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMinIdle(5);

        //集群主机集合
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        hostAndPortSet.add(new HostAndPort("192.168.1.187",6001));
        hostAndPortSet.add(new HostAndPort("192.168.1.187",6002));
        hostAndPortSet.add(new HostAndPort("192.168.1.187",6003));
        hostAndPortSet.add(new HostAndPort("192.168.1.187",6004));
        hostAndPortSet.add(new HostAndPort("192.168.1.187",6005));
        hostAndPortSet.add(new HostAndPort("192.168.1.187",6006));

        jedisCluster = new JedisCluster(hostAndPortSet,poolConfig);
    }


    @Test
    public void setName() throws IOException {
        jedisCluster.set("user:1001","Hello,Cluster");
        jedisCluster.close();
    }


    @Test
    public void getName() throws IOException {
        String name = jedisCluster.get("user:1001");
        System.out.println(name);
        jedisCluster.close();
    }
}
