(ns p072
  (:require [util :refer [pow factorize]]))

(def limit 1000000)

(defn phi
  ([p k]
   (* (pow p k) (- 1 (/ 1 p))))
  ([n]
   (->> (factorize n)
        (map (fn [[p k]] (phi p k)))
        (apply *))))

(defn solve []
  (->> (range 2 (inc limit))
       (pmap phi)
       (apply +)))
