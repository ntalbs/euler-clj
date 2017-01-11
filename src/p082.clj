(ns p082
  (:require [util :refer [parse-int split]]))

(def ^:private m
  (->> (slurp "data/matrix.txt")
       (split  #"\r\n")
       (mapv (fn [line] (mapv parse-int (split #"," line ))))))

(defn min-sum [vs [x y]]
  (if (empty? vs)
    (conj vs (+ x y))
    (conj vs (min (+ x y) (+ y (last vs))))))

(defn sweep [xs ys]
  (let [cs1 (reduce min-sum [] (map vector xs ys))
        cs2 (reverse (reduce min-sum [] (reverse (map vector xs ys))))]
    (map min cs1 cs2)))

(defn solve []
  (->> (apply mapv vector m)            ; transpose
       ;; (reverse)
       (reduce sweep)
       (apply min)))
