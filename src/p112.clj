(ns p112
  (:require [util :refer [digits]]))

(defn bouncy? [n]
  (let [ds (digits n)]
    (cond (apply <= ds) false
          (apply >= ds) false
          :else true)))

(defn solve []
  (->> (iterate inc 1)
       (filter bouncy?)
       (map-indexed (fn [i e] [(inc i) e]))
       (drop-while (fn [[i e]] (< (/ i e) 0.99)))
       first
       second))
