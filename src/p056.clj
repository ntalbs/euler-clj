(ns p056
  (:require [util :refer (pow digits)]))

(defn solve []
  (->> (for [a (range 1 100) b (range 1 100)] (pow a b))
       (map digits)
       (map #(apply + %))
       (apply max)))
