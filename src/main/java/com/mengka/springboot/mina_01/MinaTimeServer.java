package com.mengka.springboot.mina_01;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;


/**
 * ¡·¡·mina:
 * https://mina.apache.org/mina-project/userguide/user-guide-toc.html
 *
 * ¡·¡·Æô¶¯MinaTimeServer;
 *
 * ¡·¡··¢ËÍmessage£º
 * telnet 127.0.0.1 8077
 *
 * @author mengka
 * @date 2017/08/31.
 */
public class MinaTimeServer {

    public static void main(String[] args) throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new TimeServerHandler());

        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.bind(new InetSocketAddress(Constant.PORT));
    }
}
