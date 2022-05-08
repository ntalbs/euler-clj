(ns p074
  (:require [util :refer [digits]]))

(defn- fact [n]
  (case n
    0 1
    1 1
    2 2
    3 6
    4 24
    5 120
    6 720
    7 5040
    8 40320
    9 362880))

(defn- fact-sum [n]
  (->> (digits n)
       (map fact)
       (apply +)))

(defn- chain-length [n]
  (loop [s n chain #{n} cnt 1]
    (let [x (fact-sum s)]
      (if (contains? chain x)
        cnt
        (recur x (conj chain x) (inc cnt))))))

;; first approach. slow.
(defn solve1 []
  (->> (range 1 (inc 1000000))
       (filter #(= 60 (chain-length %)))
       count))

;; second approach.
(defn- sorted-digits-num [n]
  (->> (digits n)
       (sort >)
       (apply str)
       (Integer/parseInt)))

(defn solve2 []
  (->> (range 3 (inc 1000000))
       (map sorted-digits-num)
       frequencies
       (map (fn [[k cnt]] [(chain-length k) cnt]))
       (filter (fn [[k _]] (= k 60)))
       (map second)
       (apply +)))

(defn solve []
  (solve2))
