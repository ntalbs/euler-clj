;; 인터넷에서 답을 찾아보면 6자리수 소수여야 하고, 반복되는 자리는 3개여야 한다는 말이 나오는데,
;; 왜 그런지 이해가 되지 않음. 그냥 무식하게 다 해본다.

(ns p051
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.math.combinatorics :refer [permutations]]
            [util :refer [digits]]))

(def pattern5digits
  (lazy-cat (permutations [0 0 0 0 1])
            (permutations [0 0 0 1 1])
            (permutations [0 0 1 1 1])
            (permutations [0 1 1 1 1])))

(def pattern6digits
  (lazy-cat (permutations [0 0 0 0 0 1])
            (permutations [0 0 0 0 1 1])
            (permutations [0 0 0 1 1 1])
            (permutations [0 0 1 1 1 1])
            (permutations [0 1 1 1 1 1])))

(def primes5digits
  (->> primes (drop-while #(< % 10000)) (take-while #(< % 100000))))

(def primes6digits
  (->> primes (drop-while #(< % 100000)) (take-while #(< % 1000000))))

(defn group-key [pattern digits]
  (->> (map #(if (zero? %1) \* %2) pattern digits)
       (apply str)))

(defn match? [pattern digits]
  (->> (map vector pattern digits)
       (filter (fn [[p _]] (zero? p)))
       (map last)
       (apply =)))

(defn p051 [primes patterns]
  (->> (for [p patterns]
         (->> (filter #(match? p (digits %)) primes)
              (group-by #(group-key p (digits %)))))
       (apply merge)
       (filter (fn [[_ ps]] (= 8 (count ps))))))

(defn solve []
  (time (p051 primes5digits pattern5digits))
  (time (p051 primes6digits pattern6digits)))
