Kafka Notes
Streaming platfrom
main classificatin
	real time data pipeline for data between application or system
	real time streaming application that can process them

Basic Features
	can run on multiple servers
	stores streams of records in topics
	each record have -> key,value and timestamp

4 Cores of Kafka
	Producer API(): allows app to publish to a particular topic
	Consumer API(): allows app to subscribe to one or more topics
	Streams API(): allows to process or transform
	Connector API(): connects to apps or data store


-A topic is a catogory of name which stores all the values
-For each topic kalfa keeps a partition log

```
#Starting kafka and zookeeper

bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties
#creating topic in kafka



 ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
 ```
