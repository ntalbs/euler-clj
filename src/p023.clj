;; Find the sum of all the positive integers
;; which cannot be written as the sum of two abundant numbers.

(ns p023
  (:require [util :refer [aliquot-sum3]]))

(def limit 28123)

(defn abundant?
  "Returns true if n is abundant number."
  [n]
  (> (aliquot-sum3 n) n))

(defn solve []
  (let [abundants (filter abundant? (range 12 (inc (- limit 12))))
        sum-of-2-abundants (set (for [a1 abundants a2 abundants
                                      :when (<= a1 a2)
                                      :when (<= (+ a1 a2) limit)]
                                  (+ a1 a2)))]
    (->> (range 1 (inc limit))
         (remove #(sum-of-2-abundants %))
         (apply +))))
