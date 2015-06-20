;; Evaluate the sum of all the amicable numbers under 10000.

(ns p021
  (:require [util :refer [aliquot-sum aliquot-sum-by-formula]]))

(defn amicable? [a f]
  (let [b (f a)]
    (and (not= a b) (= a (f b)))))

(defn solve1 []
  (apply + (filter #(amicable? % aliquot-sum) (range 1 10000))))

(defn solve2 []
  (apply + (filter #(amicable? % aliquot-sum-by-formula) (range 1 10000))))
