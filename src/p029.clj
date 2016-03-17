;; How many distinct terms are in the sequence generated by a^b
;; for 2 <= a <= 100 and 2 <= b <=  100?

(ns p029
  (:require [util :refer [pow digits digits*]]))

(defn solve1 []
  (let [rng (range 2 (inc 100))]
    (->> (for [a rng b rng] (pow a b))
         set
         count)))

;; BigInt를 쓰지 않고 숫자 시퀀스로 푼 방법.
;; 너무 느리다.
(defn- digits-pow [x n]
  (loop [n (dec n) acc (digits x)]
    (if (<= n 0)
      acc
      (recur (dec n) (digits* acc x)))))

(defn solve2 []
  (let [rng (range 2 (inc 100))]
    (->> (for [a rng b rng] (digits-pow a b))
         set
         count)))
