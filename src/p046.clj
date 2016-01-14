(ns p046
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.set :refer [difference]]))

(def limit 10000)

(def ps (take-while #(< % limit) primes))

(def odd-composite-nums
  (difference (set (range 3 limit 2))
              ps))

(def goldbach-nums
  (set (for [p ps n (range 1 (Math/sqrt limit))] (+ p (* 2 n n)))))

(defn solve []
  (apply min (difference odd-composite-nums goldbach-nums)))
