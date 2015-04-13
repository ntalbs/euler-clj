(ns p020
  (:require [util :refer [digits factorial digits*]]))

(defn solve1 []
  (->> (factorial 100)
       digits
       (apply +)))

(defn solve2 []
  (->> (range 1 (inc 100))
       (reduce digits* [1])
       (apply +)))
