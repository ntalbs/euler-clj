;; #058

(ns p058
  (:require [util :refer (prime?)]))

(defn square [n] (* n n))

(defn l
  "compute diagonal elements on i-th ring"
  ([i n] (+ (-> (* 2 i) (- 2) (* n)) (square (-> (* 2 i) (- 3)))))
  ([i] (map #(l i %) (range 1 5))))

(def d (for [i (iterate inc 2)] (l i)))

(defn cnt-prime [coll]
  (count (filter prime? coll)))

(defn accumulate [acc next]
  (if (map? acc)
    {:width (+ 2 (:width acc))
     :prime (+ (:prime acc) (cnt-prime next))
     :total (+ (:total acc) (count next))}
    {:width 3
     :prime (+ (cnt-prime acc) (cnt-prime next))
     :total (+ (count acc) (count next))}))

(defn p058 []
  (->> (reductions accumulate {:width 1 :prime 0 :total 1} d)
       (rest) ;; skip first
       (map (fn [a] {:width (:width a) :r (/ (:prime a) (:total a))}))
       (drop-while (fn [m] (>= (:r m) 0.1)))
       (first)))

(defn solve []
  (time (println (p058))))
