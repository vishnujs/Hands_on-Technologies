ps -ef | grep java
curl -X 'POST' -H 'Content-Type:application/json' -d @examples/DatasetBEQuery.json http://sbslablx08:8082/druid/v2/sql

curl -X 'POST' -H 'Content-Type:application/json' -d @examples/DatasetBE1.json http://sbslablx08:8090/druid/indexer/v1/task

curl -X 'POST' -H 'Content-Type:application/json' -d @examples/DatasetBEQuery.json http://sbslablx08:8082/druid/v2?pretty

/home/bepoc/druid-0.12.3/conf/druid

Starting Zookeeper

curl http://www.gtlib.gatech.edu/pub/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz -o zookeeper-3.4.10.tar.gz
tar -xzf zookeeper-3.4.10.tar.gz
cd zookeeper-3.4.10
cp conf/zoo_sample.cfg conf/zoo.cfg
./bin/zkServer.sh start


Starting druid

bin/init

java `cat examples/conf/druid/coordinator/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/coordinator:lib/*" io.druid.cli.Main server coordinator
java `cat examples/conf/druid/overlord/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/overlord:lib/*" io.druid.cli.Main server overlord
java `cat examples/conf/druid/historical/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/historical:lib/*" io.druid.cli.Main server historical
java `cat examples/conf/druid/middleManager/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/middleManager:lib/*" io.druid.cli.Main server middleManager
java `cat examples/conf/druid/broker/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/broker:lib/*" io.druid.cli.Main server broker

nohup java `cat examples/conf/druid/coordinator/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/coordinator:lib/*" io.druid.cli.Main server coordinator > log/coordiantor.log 2>&1  & \
nohup java `cat examples/conf/druid/overlord/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/overlord:lib/*" io.druid.cli.Main server overlord >log/overload.log 2>&1 & \
nohup java `cat examples/conf/druid/historical/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/historical:lib/*" io.druid.cli.Main server historical > log/historical.log 2>&1 & \
nohup java `cat examples/conf/druid/middleManager/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/middleManager:lib/*" io.druid.cli.Main server middleManager>log/middleManager.log 2>&1 & \
nohup java `cat examples/conf/druid/broker/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/broker:lib/*" io.druid.cli.Main server broker > log/brocker.log 2>&1 &


nohup java `cat conf/druid/middleManager/jvm.config | xargs` -cp conf/druid/_common:conf/druid/middleManager:lib/* io.druid.cli.Main server middleManager>log/middleManager.log 2>&1 &

nohup java `cat conf/druid/broker/jvm.config | xargs` -cp conf/druid/_common:conf/druid/broker:lib/* io.druid.cli.Main server broker > log/brocker.log 2>&1 &

nohup java `cat conf/druid/historical/jvm.config | xargs` -cp conf/druid/_common:conf/druid/historical:lib/* io.druid.cli.Main server historical > log/historical.log 2>&1 &

nohup java `cat conf/druid/middleManager/jvm.config | xargs` -cp conf/druid/_common:conf/druid/middleManager:lib/* io.druid.cli.Main server middleManager > log/middleManager.log 2>&1 &

nohup java `cat conf/druid/coordinator/jvm.config | xargs` -cp conf/druid/_common:conf/druid/coordinator:lib/* io.druid.cli.Main server coordinator > log/cordinator.log 2>&1 &



nohup java `cat conf/druid/coordinator/jvm.config | xargs` -cp conf/druid/_common:conf/druid/coordinator:lib/* io.druid.cli.Main server coordinator log/coordiantor.log 2>&1 & 
nohup java `cat conf/druid/overlord/jvm.config | xargs` -cp conf/druid/_common:conf/druid/overlord:lib/* io.druid.cli.Main server overlord log/overload.log 2>&1 &

Stopping druid

ps -ef | grep druid

rm -rf log && \
rm -rf var && \
bin/init

#do this if only there is no other java programs running
 for pid in $(ps ax | grep -i 'java' | grep -v grep | awk '{print $1}'); do kill $pid; done

./bin/zkServer.sh stop
rm -rf /tmp/zookeeper


ps -ef |grep java

java `cat conf/druid/overlord/jvm.config | xargs` -cp "examples/conf/druid/_common:examples/conf/druid/_common/hadoop-xml:examples/conf/druid/overlord:lib/*" io.druid.cli.Main server overlord


curl -X 'POST' -H 'Content-Type:application/json' -d @job/wikipedia-index-sql2.json http://sbslablx08:8082/druid/v2/sql

curl -X 'POST' -H 'Content-Type:application/json' -d @job/wikipedia-index2.json.json http://sbslablx08:8090/druid/indexer/v1/task

curl -X 'POST' -H 'Content-Type:application/json' -d @examples/DatasetBEQuery.json http://sbslablx08:8082/druid/v2/sqlCo



#Starting kafka and zookeeper

bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties
#creating topic in kafka



 ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka
 ./bin/kafka-topics.sh --list --zookeeper localhost:2181
 #kafka/config/server.properties file.
 	#delete.topic.enable = true
 ./bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic Hello-kafka
#for finding the data in a particular topic
 bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic wikipedia --from-beginning

#Listing all the pid of a particular process
 for pid in $(ps ax | grep -i 'kafka' | grep -v grep | awk '{print $1}'); do echo $pid; done

 for pid in $(ps ax | grep -i 'java' | grep -v grep | awk '{print $1}'); do echo $pid; done

 nohup bin/zookeeper-server-start.sh config/zookeeper.properties > logs/zookeeper_Console.log 2>&1 &
 nohup bin/kafka-server-start.sh config/server.properties > logs/kafka_Console.log 2>&1 &
#loading the supervisor
 curl -XPOST -H'Content-Type: application/json' -d @examples/wikipedia-kafka-supervisor.json http://sbslablx08:8090/druid/indexer/v1/supervisor
#Loading data
export KAFKA_OPTS="-Dfile.encoding=UTF-8" && \
./bin/kafka-console-producer.sh --broker-list sbslablx06:9092 --topic wikipedia < /home/bepoc/druid-0.12.3/quickstart/wikiticker-2015-09-12-sampled.json

curl -X 'POST' -H 'Content-Type:application/json' -d @examples/wikipedia-top-pages.json http://sbslablx06:8082/druid/v2?pretty

sed -i '/dataSource/c\   \"dataSource\" : \"customer123\",' customerSumMonthly.json



sed -i '/dataSource/c   "dataSource" : "customer123",' customerAverageMonthly.json  \ 
sed -i '/dataSource/c   "dataSource" : "customer123",' customerCountMonthly.json  \ 
sed -i '/dataSource/c   "dataSource" : "customer123",' customerMaxMonthly.json  \ 
sed -i '/dataSource/c   "dataSource" : "customer123",' customerMinMonthly.json  \ 
sed -i '/dataSource/c   "dataSource" : "customer123",' customerSumMonthly.jsons

curl -w "@curl-format.txt" -X 'POST' -H 'Content-Type:application/json' -d @job/customerSumMonthly.json http://sbslablx06:8082/druid/v2?pretty
curl -w "@curl-format.txt" -X 'POST' -H 'Content-Type:application/json' -d @job/customerMinMonthly.json http://sbslablx06:8082/druid/v2?pretty
curl -w "@curl-format.txt" -X 'POST' -H 'Content-Type:application/json' -d @job/customerMaxMonthly.json http://sbslablx06:8082/druid/v2?pretty
curl -w "@curl-format.txt" -X 'POST' -H 'Content-Type:application/json' -d @job/customerAverageMonthly.json http://sbslablx06:8082/druid/v2?pretty
curl -w "@curl-format.txt" -X 'POST' -H 'Content-Type:application/json' -d @job/customerCountMonthly.json http://sbslablx06:8082/druid/v2?pretty



jar xf jar-file [archived-file(s)]






