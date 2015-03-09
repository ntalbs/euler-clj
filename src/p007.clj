;; 10,001st prime number?

(ns p007
  (:require [util :refer [prime?]]
            [clojure.contrib.lazy-seqs :refer [primes]]))

(def limit 10001)

(defn using-pred []
  (->> (iterate inc 2)
       (filter prime?)
       (drop (dec limit))
       first))

(defn using-seq []
  (->> primes
       (drop (dec limit))
       first))

(defn solve []
  (time (println "using-pred => " (using-pred)))
  (time (println "using-seq => " (using-seq))))
