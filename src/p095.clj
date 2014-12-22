;; slow. need to be improved.

(ns p095
  (:require [util :refer [proper-divisors]]))

(def limit 1000000)

(defn sum-of-divs [n]
  (cond (nil? n) nil
        (= n 1) nil
        (> n limit) nil
        :else (apply + (proper-divisors n))))

(def sum-of-divs (memoize sum-of-divs))

(defn find-chain [n]
  (loop [n n s #{} acc []]
    (if-not (contains? s n)
      (recur (sum-of-divs n) (conj s n) (conj acc n))
      (if (or (nil? (last acc)) (not= n (first acc))) nil acc))))

(defn longest-chain [limit]
  (->> (range 2 (inc limit))
       (map find-chain)
       (remove nil?)
       (apply max-key count)))

(defn solve []
  (time (println (apply min (longest-chain limit)))))
