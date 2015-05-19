;; Find the sum of all the positive integers
;; which cannot be written as the sum of two abundant numbers.

(ns p023
  (:require [util :refer [divisor? sum-of-proper-divisor]]))

(def limit 28123)

(defn abundant?
  "Returns true if n is abundant number."
  [n]
  (> (sum-of-proper-divisor n) n))

;; takes over 17 secs. need to improve.
(defn p023 []
  (let [abundants (filter abundant? (range 12 (inc (- limit 12))))
        sum-of-2-abundants (reduce conj #{}
                                   (for [a1 abundants a2 abundants]
                                     (+ a1 a2)))]
    (->> (range 1 (inc limit))
         (filter (fn [i] (nil? (sum-of-2-abundants i))))
         (apply +))))

(defn solve []
  (time (println (p023))))
