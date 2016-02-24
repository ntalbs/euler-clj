(ns p052
  (:require [util :refer [digits]]))

(defn check [v]
  (->> (map digits v)
       (map sort)
       (apply =)))

(defn solve []
  (->> (iterate inc 1)
       (map (fn [n] (for [i [1 2 3 4 5 6]] (* i n))))
       (filter #(check %))
       ffirst))
