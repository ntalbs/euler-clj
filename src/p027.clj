(ns p027
  (:require [util :refer [prime?]]))

(defn f [a b]
  (fn [n]
    (+ (* n n) (* a n) b)))

(defn prime-count [a b]
  (->> (iterate inc 0)
       (map (f a b))
       (take-while prime?)
       count))

(defn solve1 []
  (let [lower -999 upper 1000]
    (->> (for [a (range lower upper)
               b (range 2 upper)
               :when (prime? b)
               :when (prime? (+ 1 a b))]
           {:a*b (* a b) :cnt (prime-count a b)})
         (apply max-key :cnt)
         :a*b)))

; takes over 17 secs. need to improve.
(defn p027 []
  (reduce (fn [a b] (if (< (:cnt a) (:cnt b)) b a)) (seq-of-prime-count)))

(defn solve []
  (time (println (p027))))
