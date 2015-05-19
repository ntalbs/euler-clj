(ns p047
  (:require [util :refer [factorize]]))

(defn count-prime-factor [n]
  (count (factorize n)))

(defn p047 []
  (->> (iterate inc 1)
       (partition 4 1)
       (filter (fn [ns] (every? #(= 4 %) (map count-prime-factor ns))))
       first
       first))

(defn solve []
  (time (println (p047))))
