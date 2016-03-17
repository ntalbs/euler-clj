(ns p058
  (:require [util :refer (prime?)]))

(defn- square [n] (* n n))

(defn- d
  "n번째 ring의 대각선 요소 수를 구하는 함수.
   d(n,k) = 2n(k+1) + (2n-1)^2
   n=1,2,3..., k=(0,1,2,3)"
  ([n k] (+ (* 2 n (inc k)) (square (dec (* 2 n)))))
  ([n]   (map #(d n %) (range 3))))

(defn- cnt-prime [coll]
  (count (filter prime? coll)))

(defn solve []
  (->> (iterate inc 1)
       (map d)
       (reductions (fn [[w pc tc] ns]
                     [(+ 2 w) (+ pc (cnt-prime ns)) (+ tc 4)])
                   [1 0 1])
       (rest)
       (map (fn [[w pc tc]] [w (/ pc tc)]))
       (drop-while (fn [[_ r]] (>= r 0.1)))
       ffirst))
