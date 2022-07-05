import org.apache.spark.graphx.GraphLoader
val graph = GraphLoader.edgeListFile(sc, "email-Eu-core.txt")
val t1 = System.nanoTime
val ranks = graph.pageRank(0.0001).vertices
val duration = (System.nanoTime - t1) / 1e9d
println("Total time taken ", duration)


val users = sc.textFile("node_ids.txt").map { line =>
  val fields = line.split("\t")
  (fields(0).toLong, fields(1))
}
val ranksByUsername = users.join(ranks).map {
  case (id, (username, rank)) => (username, rank)
}

ranksByUsername.coalesce(1).saveAsTextFile("GraphX_Out")
println(ranksByUsername.collect().mkString("\n"))