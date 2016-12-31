(ns p082
  (:require [util :refer [parse-int split]]))

(def ^:private m
  (->> (slurp "data/matrix.txt")
       (split  #"\r\n")
       (mapv (fn [line] (mapv parse-int (split #"," line ))))))

(defn cost [vs [x y]]
  (if (empty? vs)
    (conj vs (+ x y))
    (conj vs (min (+ x y) (+ y (last vs))))))

(defn sweep-row [xs ys]                 ; from top to bottom
  (let [cs1 (reduce cost [] (map vector xs ys))
        cs2 (reverse (reduce cost [] (map vector (reverse xs) (reverse ys))))]
    (map min cs1 cs2)))

(defn solve [m]
  (->> (apply mapv vector m)            ; transpose
       (reverse)
       (reduce sweep-row)
       (apply min)))
