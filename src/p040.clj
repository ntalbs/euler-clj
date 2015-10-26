(ns p040
  (:require [util :refer [digits pow]]))

(def ds (mapcat digits (iterate inc 1)))

(defn solve []
  (->> (range 7)
       (map #(pow 10 %))
       (map #(nth ds (dec %)))
       (apply *)))
