(ns p037
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [parse-int prime? digits pow]]))

;; 방법 1

(defn- to-int [digits]
  (parse-int (apply str digits)))

(defn- truncated-nums
  [num]
  (let [digits (digits num) n (count digits)]
    (->> (for [i (range 1 n)]
           [(to-int (drop i digits)) (to-int (take i digits))])
         flatten
         set)))

(defn- all-prime? [nums]
  (= (count nums)
     (count (take-while prime? nums))))

(defn solve1 []
  (->> (drop 4 primes) ; drop 2, 3, 5, 7
       (filter  (fn [n] (all-prime? (truncated-nums n))))
       (take 11)
       (apply +)))


;; 방법 2

(defn- truncate-right [n]
  (quot n 10))

(defn- right-truncatable-prime? [p]
  (loop [n (truncate-right p)]
    (if (prime? n)
      (if (< n 10)
        true
        (recur (truncate-right n))))))

(defn- truncate-left [n]
  (rem n (pow 10 (int (Math/log10 n)))))

(defn- left-truncatable-prime? [p]
  (loop [n (truncate-left p)]
    (if (prime? n)
      (if (< n 10)
        true
        (recur (truncate-left n))))))

(defn solve2 []
  (->> primes
       (drop 4) ; drop 2, 3, 5, 7
       (filter #(and (left-truncatable-prime? %) (right-truncatable-prime? %)))
       (take 11)
       (reduce +)))
