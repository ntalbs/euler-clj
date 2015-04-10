(ns p016
  (:require [util :refer [pow digits add-digits]]))

(defn solve1 []
  (->> (pow 2 1000)
       (digits)
       (apply +)))

(defn twice-digits [x]
  (add-digits x x))

(defn solve2 []
  (->> (iterate twice-digits [1])
       (drop 1000)
       first
       (reduce +)))
