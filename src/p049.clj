(ns p049
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.math.combinatorics :refer [permutations]]
            [util :refer [parse-int digits]]))

(def four-digits-primes
  (->> primes
       (drop-while #(< % 1000))
       (take-while #(< % 9999))
       set))

(defn permutation-primes [p]
  (->> (digits p)
       permutations
       (map #(apply str %))
       (map #(parse-int %))
       (filter four-digits-primes)
       sort))

(defn find-arithmetic-seq [v]
  (->> (for [i v, j v :when (< i j)] [i j])
       (group-by (fn [[a b]] (- b a)))
       (filter (fn [[_ ds]] (= 2 (count ds))))
       (filter (fn [[_ [[_ a] [b _]]]] (= a b)))
       (map (fn [[_ [[a b] [c d]]]] [a b d]))
       first))

(defn solve []
  (->> four-digits-primes
       (map permutation-primes)
       (map find-arithmetic-seq)
       (filter not-empty)
       distinct
       (map #(apply str %))))
