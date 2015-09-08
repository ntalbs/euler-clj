;; Find the sum of all the numbers that can be written
;; as the sum of fifth powers of their digits.

(ns p030
  (:require [util :refer [digits pow]]))

(defn sum-of-5th-power-of-digits [n]
  (->> (digits n)
       (map (fn [x] (pow x 5)))
       (apply +)))

(defn solve []
  (->> (range 2 354294)
       (filter #(= % (sum-of-5th-power-of-digits %)))
       (apply +)))
