(ns p056
  (:require [util :refer (pow digits)]))

(defn p056 []
  (apply max
         (for [a (range 1 100) b (range 1 100)]
           (->> (pow a b)
                (digits)
                (apply +)))))

(defn solve []
  (time (println (p056))))
