(ns p086
  (:require [util :refer [perfect-square?]]))

(def ^:private threshold 1000000)

(defn cnt [c]
  (apply +
         (for [l (range 3 (inc (* 2 c))) ; l <= a+b
               :when (perfect-square? (+ (* c c) (* l l)))]
           (if (<= l c)
             (quot l 2)
             (- c (quot (- l 1) 2))))))

(defn solve []
  (->> (iterate inc 3)
       (map (fn [x] [x (cnt x)]))
       (reductions (fn [[a1 a2] [b1 b2]] [b1 (+ a2 b2)]))
       (drop-while (fn [[M cnt]] (<= cnt threshold)))
       ffirst))
