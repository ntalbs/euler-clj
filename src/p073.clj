(ns p073
  (:require [util :refer [gcd infix]]))

;; brute force
(defn reduced-proper-fractions [limit]
  (for [d (range 3 (inc limit))
        n (range (int (/ d 3)) (inc (int (/ d 2))))
        :when (< 1/3 (/ (double n) d) 1/2)
        :when (= 1 (gcd n d))]
    (/ n d)))

(defn solve1 []
  (count (reduced-proper-fractions 12000)))

;; farey sequence
(defn- next-to [a b limit]
  (->> (range 2 limit)                                ; d
       (map (fn [d] [(infix (((a * d) + 1) / b)) d])) ; [c d]
       (filter (fn [[c d]] (integer? c)))
       (map (fn [[c d]] (/ c d)))
       (apply min)))

;; http://blog.mishkovskyi.net/posts/2015/Oct/29/clojure-numbers-despair
(defn solve2 []
  (let [limit 12000
        a 1
        b 3
        r (next-to a b limit)
        c (numerator r)
        d (denominator r)]
    (loop [a a, b b, c (int c), d (int d), acc 0]
      (if (< (/ (double c) d) 0.5)
        (let [k (quot (+ limit b) d)]
          (recur c d (- (* k c) a) (- (* k d) b) (inc acc)))
        acc))))

(defn solve []
  (solve2))
