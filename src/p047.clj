(ns p047
  (:require [util :refer [factorize]]))

(defn count-prime-factors [n]
  (count (factorize n)))

(defn solve []
  (->> (iterate inc 1)
       (partition 4 1)
       (filter (fn [ns] (every? #(= 4 %) (map count-prime-factors1 ns))))
       ffirst))
