(ns p047
  (:require [util :refer [factorize]]))

(defn count-prime-factors [n]
  (count (factorize n)))

(defn solve []
  (->> (iterate inc 2)
       (map (fn [n] [n (count-prime-factors n)]))
       (partition 4 1)
       (filter (fn [ns] (every? #(= 4 %) (map (fn [[_ c]] c) ns))))
       first first first))
