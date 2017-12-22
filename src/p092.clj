(ns p092
  (:require [util :refer [digits factorial]]))

;; Brute-force
(defn- sum-sq-digits [n]
  (->> (digits n)
       (map #(* % %))
       (reduce +)))

(defn- reach89? [n]
  (cond (= n 1) false
        (= n 89) true
        :else (reach89? (sum-sq-digits n))))

(def reach89? (memoize reach89?))

(defn solve1 []
  (->> (range 1 10000000)
       (filter reach89?)
       (count)))

;; Combinatorics approach
(defn- cnt [ds]
  (->> ds
       (group-by identity)
       (map (fn [[_ d]] (count d)))
       (map factorial)
       (apply *)
       (quot (factorial 7))))

(defn- vn [digits]
  (reduce (fn [a b] (+ (* 10 a) b)) digits))

(defn solve2 []
  (let [r (range 0 10)]
    (->> (for [a r
               b r
               c r
               d r
               e r
               f r
               g r
               :when (<= a b c d e f g)
               :when (not= 0 a b c d e f g)] [a b c d e f g])
         (map (fn [ds] [(vn ds) (cnt ds)]))
         (filter (fn [[k cnt]] (reach89? k)))
         (map (fn [[k cnt]] cnt))
         (apply +))))
