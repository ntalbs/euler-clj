(ns p071
  (:require [util :refer [infix]]))

(def limit 1000000)

(defn solve []
  (->> (range 2 limit)                                ; b
       (map (fn [b] [(infix (((3 * b) - 1) / 7)) b])) ; [a b]
       (filter (fn [[a b]] (integer? a)))             ; a should be an int
       (map (fn [[a b]] (/ a b)))                     ; a/b
       (apply max)
       numerator))
