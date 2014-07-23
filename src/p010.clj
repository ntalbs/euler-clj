;; #010
;; sum of all the primes below 2,000,000.

(ns p010
  (:require [util :refer [prime?]]))

; initial: Takes around 10 secs. Too slow.
(defn p010-1 [bound]
  (->> (filter prime? (range 2 (inc bound)))
       (reduce +)))


; second: Use clojure.contrib.lazy-seqs primes.
(use '[clojure.contrib.lazy-seqs :only (primes)])

(defn p010-2 [bound]
  (->> (take-while #(< % bound) primes)
       (reduce +)))

(defn solve []
  (time (println (p010-1 2000000)))
  (time (println (p010-2 2000000))))
