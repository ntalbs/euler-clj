(ns p033
  (:require [util :refer [digits]]))

(defn st-cancel "strange cancel" [n d]
  (let [[n1 n2] (digits n) [d1 d2] (digits d)]
    (cond (= n1 d1) (/ n2 d2)
          (= n1 d2) (/ n2 d1)
          (= n2 d1) (/ n1 d2)
          (= n2 d2) (/ n1 d1)
          :else nil)))

(def rs
  (for [n (range 10 100) d (range (inc n) 100)
        :when (not= 0 (mod n 10))
        :when (not= 0 (mod d 10))]
    [n d]))

(defn p033 []
  (reduce *
          (map (fn [[n d]] (/ n d))
               (filter (fn [[n d]] (= (/ n d) (st-cancel [n d]))) rs))))

(defn solve []
  (time (println (p033))))
