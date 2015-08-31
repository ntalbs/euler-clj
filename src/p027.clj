(ns p027
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [prime?]]))

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

(defn solve2 []
  (->> (for [b (take-while #(< % 1000) primes)
             a (range -999 1000)
             ;; :when (let [r (+ 1 a b)] (or (= 2 r) (odd? r)))
             :when (prime? (+ 1 a b))
             ]
         {:a*b (* a b) :cnt (prime-count a b)})
       (apply max-key :cnt)
       :a*b))
