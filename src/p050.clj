(ns p050
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [prime?]]))

(def csp "indexes and cumulative sums of primes"
  (->> primes
       (reductions (fn [a p] [(inc (first a)) (+ (second a) p)]) [0 0])
       (take-while (fn [[_ s]] (< s 1000000)))))

(defn solve []
  (->> (for [csp1 csp, csp2 csp
             :let [[i1 s1] csp1, [i2 s2] csp2]
             :when (< i1 i2)]
         [(- i2 i1) (- s2 s1)])
       (filter (fn [[_ s]] (prime? s)))
       (apply max-key first)
       second))
