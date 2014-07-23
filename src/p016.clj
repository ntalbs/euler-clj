;; #016
;; sum of digits of 2^1000

(ns p016
  (:require [util :refer [digits]]))

(defn p016 []
  (->> (.pow (java.math.BigInteger. "2") 1000)
       digits
       (apply +)))

(defn solve []
  (time (println (p016))))
