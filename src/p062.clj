(ns p062
  (:require [util :refer (digits)]))

(def cubics
  (->> (iterate inc 1)
       (map #(* % % %))))

(defn sorted-digits [n]
  (sort (digits n)))

(defn solve []
  (loop [cs cubics m {}]
    (let [c  (first cs)           ; current cubic number
          k  (sorted-digits c)    ; key from c
          m  (update m k conj c)  ; map
          cl (m k)]               ; cubic list for k
      (if (= 5 (count cl))
        (apply min cl)
        (recur (rest cs) m)))))
