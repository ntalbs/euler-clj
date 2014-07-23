;; #p021
;; Evaluate the sum of all the amicable numbers under 10000.

(ns p021
  (:require [util :refer [divisor? sum-of-proper-divisor]]))

(defn amicable [a]
  (let [b (sum-of-proper-divisor a)]
    (if (and (not= a b) (= a (sum-of-proper-divisor b)))
      a
      0)))

(defn p021 []
  (apply + (map amicable (range 1 10000))))

(defn solve []
  (time (println (p021))))
