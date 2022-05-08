(ns p016
  (:require [util :refer [pow digits digits+]]))

(defn solve1 []
  (->> (pow 2 1000)
       (digits)
       (apply +)))

(defn twice-digits [x]
  (digits+ x x))

(defn solve2 []
  (->> (iterate twice-digits [1])
       (drop 1000)
       first
       (reduce +)))

(defn solve []
  (solve1))
