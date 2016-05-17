;; http://en.wikipedia.org/wiki/Continued_fraction
;; http://en.wikipedia.org/wiki/Pell%27s_equation

(ns p066
  (:require [util :refer [perfect-square?]]
            [p064 :refer [expand-continued-fraction]]))

(defn find-fundamental-solution
  "find the fundamental solution of pell's equation,  x^2 - dy^2 = 1"
  [d]
  (let [cf (expand-continued-fraction d)
        as (lazy-cat cf (cycle (rest cf)))]
    (loop [h2 0, h1 1,
           k2 1, k1 0,
           as as, n 0]
      (if (and (>= n 1) (= 1 (-' (*' h1 h1) (*' d k1 k1))))
        [h1 k1]
        (recur h1 (+' (*' (first as) h1) h2)
               k1 (+' (*' (first as) k1) k2)
               (rest as) (inc n))))))

(defn solve []
  (->> (range 2 (inc 1000))
       (filter (complement perfect-square?))
       (map (fn [d] [d (first (find-fundamental-solution d))]))
       (apply (partial max-key second))))
