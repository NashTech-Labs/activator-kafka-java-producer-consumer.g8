# activator-kafka-java-producer-consumer
This activator project proves insights about how to read &amp; write data from kafka queue.

###Download link for kafka :

    http://kafka.apache.org/downloads.html
    
##Run zookeeper and kafka on your system : 

    kafka$ bin/zookeeper-server-start.sh config/zookeeper.properties
    kafka$ bin/kafka-server-start.sh config/server.properties 

##Steps to start producer service :

    ./activator "run-main com.knoldus.demo.ProducerApp"
  
##Steps to start consumer service :

    ./activator "run-main com.knoldus.demo.ConsumerApp"
  
