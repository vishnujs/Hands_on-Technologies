Spark
  A cluster computing plarform designed to be fast and general-purpose
  A Unified stack
  Spark Core
  Spark SQL
  Spark Streaming
  MLib
  GraphX
  Cluster Managers

Introduction to core spark concepts
  driver program
  SparkContext

Note: When saving RDD data into MongoDB, it must be a type that can be converted into a Bson document. You may have add a map step to transform the data into a Document (or BsonDocument a DBObject).

Spark RDD APIs – An RDD stands for Resilient Distributed Datasets. It is Read-only partition collection of records. RDD is the fundamental data structure of Spark. It allows a programmer to perform in-memory computations on large clusters in a fault-tolerant manner. Thus, speed up the task. Follow this link to learn Spark RDD in great detail.
Spark Dataframe APIs – Unlike an RDD, data organized into named columns. For example a table in a relational database. It is an immutable distributed collection of data. DataFrame in Spark allows developers to impose a structure onto a distributed collection of data, allowing higher-level abstraction. Follow this link to learn Spark DataFrame in detail.
Spark Dataset APIs – Datasets in Apache Spark are an extension of DataFrame API which provides type-safe, object-oriented programming interface. Dataset takes advantage of Spark’s Catalyst optimizer by exposing expressions and data fields to a query planner. Follow this link to learn Spark DataSet in detail.



The map and flatMap methods have a similar purpose, but map is 1 to 1,
while flatMap is 1 to 0-N (outputting 0 is similar to a filter, except of
course it could be outputting a different type).
