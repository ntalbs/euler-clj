(ns p049
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.math.combinatorics :refer [permutations]]
            [util :refer [parse-int digits prime?]]))

(def four-digits-primes
  (->> primes
       (drop-while #(< % 1000))
       (take-while #(< % 9999))))

(defn permutation-nums [n]
  (->> (permutations (util/digits n))
       (map #(parse-int (apply str %)))
       (filter #(<= n %))
       (distinct)
       (sort)))

(defn check-permutation-primes [p]
  (->> (permutation-nums p)
       (filter prime?)
       (sort)
       (distinct)))

(defn arithmetic-seq? [v]
  (->> (partition 2 1 v)
       (map (fn [[a b]] (- b a)))
       (distinct)
       (count)
       (= 1)))

(defn p049 []
  (->> four-digits-primes
       (map check-permutation-primes)
       (filter #(<= 3 (count %)))
       (filter arithmetic-seq?)
       (map #(apply str %))))

; This code find the answer.
; However, due to the incompleteness of arithmetic-seq? funciton,
; don't find 1487, 4817, 8147.
; This is just a luck.

(defn solve []
  (time (println (p049))))
