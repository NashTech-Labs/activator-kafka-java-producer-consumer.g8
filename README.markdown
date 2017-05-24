A [Giter8][g8] template for showcasing how to read & write data from Kafka-cluster using Java Producer & Consumer API.

About
--------------

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

Template license
----------------
Written in 2016 by Knoldus Software LLP

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
