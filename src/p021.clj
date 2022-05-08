;; Evaluate the sum of all the amicable numbers under 10000.

(ns p021
  (:require [util :refer [aliquot-sum1 aliquot-sum2 aliquot-sum3]]))

(defn amicable? [a f]
  (let [b (f a)]
    (and (not= a b) (= a (f b)))))

(defn solve1 []
  (apply + (filter #(amicable? % aliquot-sum1) (range 1 10000))))

(defn solve2 []
  (apply + (filter #(amicable? % aliquot-sum2) (range 1 10000))))

(defn solve3 []
  (apply + (filter #(amicable? % aliquot-sum3) (range 1 10000))))

(defn solve []
  (solve3))
