(ns p070
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [digits factorize]]))

(defn phi [p1 p2]
  (* (dec p1) (dec p2)))

(defn permutation? [m n]
  (= (sort (digits m)) (sort (digits n))))

(def ps
  (->> primes
       (drop-while #(< % 2000))
       (take-while #(< % 4000))))

(defn p070 []
  (->> (for [p1 ps, p2 ps :when (< (* p1 p2) 10000000)]
         [p1 p2 (phi p1 p2)])
       (filter (fn [[p1 p2 phi]] (permutation? (* p1 p2) phi)))
       (map (fn [[p1 p2 phi]] (/ (* p1 p2) phi)))
       (apply min)
       numerator))

(defn solve []
  (time (println (p070))))
