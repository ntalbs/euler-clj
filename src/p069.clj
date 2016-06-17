;; Find the value of n  1,000,000 for which n/φ(n) is a maximum.

(ns p069
  (:require [clojure.contrib.lazy-seqs :refer (primes)]))

(defn solve []
  (->> (reductions * primes)
       (take-while #(< % 1000000))
       last))
