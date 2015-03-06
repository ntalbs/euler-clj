(ns p108
  (:require [util :refer [factorize]]))

(defn d [n]
  (->> (factorize n)
       (map (fn [[_ a]] (+ 1 a)))
       (apply *)))

(defn cnt [n]
  [n (/ (d (* n n)) 2)])

(defn solve []
  (->> (iterate inc 1)
       (map cnt)
       (drop-while (fn [[_ cnt]] (< cnt 1000)))
       ffirst))
