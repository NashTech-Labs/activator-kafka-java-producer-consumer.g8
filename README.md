# activator-kafka-java-producer-consumer

This activator project proves insights about how to read &amp; write data from kafka queue.

####Steps to Install and Run zookeeper and kafka on your system : 

Step 1: Download kafka from [here](http://mirror.fibergrid.in/apache/kafka/0.10.0.0/kafka_2.11-0.10.0.0.tgz)

Step2: Extract it

    $ tar -xzf kafka_2.11-0.10.0.0.tgz
    $ cd  kafka_2.11-0.10.0.0

Step3: Start the server

Start  zookeeper:

     $ bin/zookeeper-server-start.sh config/zookeeper.properties

Start  Kafka server:

    $ bin/kafka-server-start.sh config/server.properties

#### Clone Project:
   
    
    $ git clone git@github.com:knoldus/activator-kafka-java-producer-consumer.git
    
    $ cd activator-kafka-java-producer-consumer
    
    $ ./activator clean compile



####Steps to start producer service :

    $ ./activator "run-main com.knoldus.demo.ProducerApp"
  
####Steps to start consumer service :

    $  ./activator "run-main com.knoldus.demo.ConsumerApp"

  
