(ns p050
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [prime?]]))

(def csp "cumulative sums of primes"
  (->> primes
       (reductions +)
       (take-while #(< % 1000000))))

(defn solve []
  (->> (for [s (conj csp 0)] (map #(- % s) csp))
       flatten
       (filter pos?)
       (filter prime?)
       (apply max)))
