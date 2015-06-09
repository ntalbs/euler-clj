;; Evaluate the sum of all the amicable numbers under 10000.

(ns p021
  (:require [util :refer [sum-of-proper-divisor]]))

(defn amicable [a]
  (let [b (sum-of-proper-divisor a)]
    (if (and (not= a b) (= a (sum-of-proper-divisor b)))
      a
      0)))

(defn solve []
  (apply + (map amicable (range 1 10000))))
