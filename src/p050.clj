(ns p050
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [prime?]]))

(def csp "cumulative sums of primes"
  (->> primes
       (reductions + 0)
       (take-while #(< % 1000000))))

(defn solve []
  (->> (for [s csp t csp :when (< s t)] (- t s))
       (filter odd?)
       (filter prime?)
       (apply max)))
