(defproject euler "0.1.0-SNAPSHOT"
  :description "Project Euler solutions in Clojure"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/math.combinatorics "0.1.1"]
                 [org.clojure/core.logic "0.8.10"]]
  :jvm-opts ["-XX:+UnlockCommercialFeatures" "-XX:+FlightRecorder" "-XX:FlightRecorderOptions=defaultrecording=true"]
)
