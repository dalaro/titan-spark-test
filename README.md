
    # Start titan05 at the desired version, load GraphOfTheGods
    # ...
    # In the repo root:
    mvn clean package -DskipTests=true
    # SPARK_HOME=~/spark-1.2.1 or whatever
    cd $SPARK_HOME
    bin/spark-submit \
        --class "com.thinkaurelius.titan.test.TitanApp" \
        --master 'local[4]' \
        ../titan-spark-test/target/titan-spark-test-0.0.1-SNAPSHOT-jar-with-dependencies.jar
