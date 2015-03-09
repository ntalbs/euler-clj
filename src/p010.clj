;; sum of all the primes below 2,000,000.

(ns p010
  (:require [util :refer [prime?]]
            [clojure.contrib.lazy-seqs :refer [primes]]))

(def limit 2000000)

; initial: Takes around 10 secs. Too slow.
(defn using-pred []
  (->> (filter prime? (range 2 (inc limit)))
       (reduce +)))

; second: Use clojure.contrib.lazy-seqs primes.
(defn using-seq []
  (->> (take-while #(< % limit) primes)
       (reduce +)))

(defn solve []
  (time (println (using-pred)))
  (time (println (using-seq))))
