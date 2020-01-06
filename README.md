# Creating Docker MQ

Create a custom [mq-docker](https://github.com/ibm-messaging/mq-docker/) image using the following Dockerfile:
```
FROM ibmcom/mq
COPY config.mqsc /etc/mqm/
```

When the image is run, <bold>config.mqsc<bold> will be automatically used to configure IBM MQ:
```
DEFINE CHANNEL(BOS.SVRCONN) CHLTYPE(SVRCONN) TRPTYPE(TCP) MCAUSER('bos') REPLACE

DEFINE QLOCAL(ORGANISATION.EVENTS.BACKOUT.QUEUE) REPLACE
DEFINE QLOCAL(ORGANISATION.EVENTS.BOS.A) BOTHRESH(1) BOQNAME(ORGANISATION.EVENTS.BACKOUT.QUEUE) REPLACE
```

# Web Console

The web console can be accessed at: https://localhost:9443/ibmmq/console/.

# Useful Links

* [Docker IBM MQ](https://hub.docker.com/r/ibmcom/mq/)
* [Write and run your first IBM MQ JMS application](https://developer.ibm.com/messaging/learn-mq/mq-tutorials/develop-mq-jms/) - useful for figuring out how to configure a vanilla Spring application.
* [Creating a message channel](https://www.ibm.com/support/knowledgecenter/SSFKSJ_9.0.0/com.ibm.mq.explorer.doc/bi00239_.htm)
* [MQ JMS application development with Spring Boot](https://developer.ibm.com/messaging/2018/04/03/mq-jms-spring-boot/)
* [Github IBM MQ](https://github.com/ibm-messaging/mq-jms-spring) - contains sample code
