(ns p040
  (:require [util :refer [digits pow]]))

(def ds (mapcat digits (iterate inc 1)))

(defn d [n]
  (nth ds n))

(defn p040[]
  (->> (range 7)
       (map #(pow 10 %))
       (map #(nth ds (dec %)))
       (apply *)))

(defn solve []
  (time (println (p040))))
