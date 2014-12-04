(ns p060
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.math.combinatorics :refer [combinations]]
            [util :refer [pow prime?]]))

(defn concat-nums [a b]
  (let [m (inc (int (Math/log10 b)))]
    (+ (* a (pow 10 m)) b)))

(defn concated-prime? [a b]
  (and (prime? (concat-nums a b))
       (prime? (concat-nums b a))))

(def concated-prime? (memoize concated-prime?))

(defn any-two-concat-prime? [ps]
  (->> (combinations ps 2)
       (drop-while (fn [[a b]] (concated-prime? a b)))
       empty?))

(def ps1
  (take-while #(< % 10000) (drop 1 primes)))

(def ps2
  (for [p1 ps1, p2 ps1
        :when (< p1 p2)
        :when (concated-prime? p1 p2)]
    [p1 p2]))

(def ps3
  (for [[p1 p2] ps2, p3 ps1
        :when (< p1 p2 p3)
        :when (any-two-concat-prime? [p1 p2 p3])]
    [p1 p2 p3]))

(def ps4
  (for [[p1 p2 p3] ps3, p4 ps1
        :when (< p1 p2 p3 p4)
        :when (any-two-concat-prime? [p1 p2 p3 p4])]
    [p1 p2 p3 p4]))

(def ps5
  (for [[p1 p2 p3 p4] ps4 p5 ps1
        :when (< p1 p2 p3 p4 p5)
        :when (any-two-concat-prime? [p1 p2 p3 p4 p5])]
    [p1 p2 p3 p4 p5]))

(defn p060 []
  (apply + (first ps5)))

(defn solve []
  (time (println (p060))))
